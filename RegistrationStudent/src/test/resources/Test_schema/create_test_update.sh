#!/bin/bash
cd `dirname $0`

# Read db values from properties file
. dbValues_test.properties

echo "Creating database if not created ...."
mysql -u$dbUser -p$dbPassword < create_test_schema.ddl

echo "Find last script name run on db ..."
lastScript='mysql -u$dbUser -p$dbPassword $database < last_script_run.sql | tail -1'
lastScript=`eval $lastScript`
lastScriptNumber=${lastScript:0:3}
passedLastExecutedScript=false


# For all sql or ddl scripts
#cd update
cd ../../../main/resources/SQL/
for updateScriptName in [0-9]*.[ds][dqh]*
do
	# See if this script has executed	
	command='echo "select count(*) from version where script_name=\"'$updateScriptName'\"" | mysql -u$dbUser -p$dbPassword $database | tail -n1'
	scriptHasBeenExecuted=`eval $command`
	
	scriptNumber=${updateScriptName:0:3}
	
	if [ "$scriptHasBeenExecuted" -eq 0 ] 
	then
		# Script has not been executed
		
		#Detect if DDL/SQL
		scriptLength=${#updateScriptName}
		scriptType=${updateScriptName:scriptLength-3:3}
		
		#skip test db if sql
		if [ $database != "amstestdb" -a $scriptType == "sql" ] 
		then
			continue
		fi
		
		#skip test db if sh
		if [ $database != "amstestdb" -a $scriptType == ".sh" ] 
		then
			continue
		fi
		
		script=$updateScriptName
		
		# Inserting 'pending' token in 'version' table
		echo "Inserting entry in 'version' table for "$script
		mysql -u$dbUser -p$dbPassword $database -e"insert into version (script_name, date_run) values ('$updateScriptName', now())" || exit 1
		
		if [ $? -eq 0 ]
		then
			# Execute script
			echo "executing "$script
			if [ $scriptType == ".sh" ] 
			then
				sh $script
			else
				mysql -u$dbUser -p$dbPassword $database < $script
			fi
			
			# Determine if execution was successful
			if [ $? -eq 0 ]
			then
				# update token on successful execution in database so it will not run again
				echo "Execution was successful for "$updateScriptName
			else
				# Report error and cease processing
				echo "Execution failed!!"  
				cd -
				exit 1
			fi
		fi
		
	else
		echo "The following script has previously been executed="$updateScriptName
	fi	
	
	if [ "$scriptNumber" = "$lastScriptNumber" -a "$passedLastExecutedScript" = false ]
	then
		echo "*** We have now passed the point reached on the last update"
		passedLastExecutedScript=true
	fi
done
