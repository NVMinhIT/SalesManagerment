package com.example.salesmanagerment.data.model.request;

public class ChangePasswordRequest
{
    //[dbo].[Proc_ChangePassword]
    public String UserName;
    public String OldPass ;
    public String NewPass;

    public ChangePasswordRequest(String userName, String oldPass, String newPass) {
        UserName = userName;
        OldPass = oldPass;
        NewPass = newPass;
    }
}