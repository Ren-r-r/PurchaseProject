package com.ryz.entity;

import java.util.List;

public class EasyUIDataGrid {

    private Integer total;//总个数
    private List<?> rows;//数据集合


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
