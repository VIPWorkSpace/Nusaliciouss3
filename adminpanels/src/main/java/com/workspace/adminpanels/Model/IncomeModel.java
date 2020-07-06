package com.workspace.adminpanels.Model;

public class IncomeModel {
    Integer id;
    Integer total;

    public IncomeModel() {
    }

    public IncomeModel(Integer id, Integer totalr) {
        this.id = id;
        this.total = total;;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
