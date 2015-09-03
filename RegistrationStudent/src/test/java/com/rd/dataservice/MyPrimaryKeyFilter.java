package com.rd.dataservice;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.filter.IColumnFilter;

class MyPrimaryKeyFilter implements IColumnFilter {
        private String pseudoKey = null;

        MyPrimaryKeyFilter(String pseudoKey) {
            this.pseudoKey = pseudoKey;
        }

        public boolean accept(String tableName, Column column) {
            return column.getColumnName().equalsIgnoreCase(pseudoKey);
        }

}

