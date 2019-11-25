package com.example.salesmanagerment.data.model.request;

public class ChangePassRequest {
    private String UserName = "";
    private String OldPass = "";
    private String NewPass = "";

    public ChangePassRequest() {

    }

    public ChangePassRequest(String userName, String oldPass, String newPass) {
        UserName = userName;
        OldPass = oldPass;
        NewPass = newPass;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getOldPass() {
        return OldPass;
    }

    public void setOldPass(String oldPass) {
        OldPass = oldPass;
    }

    public String getNewPass() {
        return NewPass;
    }

    public void setNewPass(String newPass) {
        NewPass = newPass;
    }
}
