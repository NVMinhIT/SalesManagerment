package com.example.salesmanagerment.data.model.response;

public class Table {
    private String idTable;
    private String nameTable;
    private int statusTable; //0: trống, 1:đang phục vụ, 2: đã đặt trước

    public Table() {

    }

    public Table(String idTable, String nameTable, int statusTable) {
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.statusTable = statusTable;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getStatusTable() {
        return statusTable;
    }

    public void setStatusTable(int statusTable) {
        this.statusTable = statusTable;
    }
}
