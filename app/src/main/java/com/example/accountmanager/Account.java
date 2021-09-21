package com.example.accountmanager;

public class Account{
    private String accName;
    private String accPass;
    private String accEmail;

    public Account(){}

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public void setAccEmail(String accEmail) {
        this.accEmail = accEmail;
    }

    public String getAccName() {
        return accName;
    }

    public String getAccPass() {
        return accPass;
    }

    public String getAccEmail() {
        return accEmail;
    }
}
