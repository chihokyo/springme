package com.jdbctemp.entity;

public class UserTable {

    private String user;
    private String password;
    private int balance;

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", password='" + getPassword() + "'" +
            ", balance='" + getBalance() + "'" +
            "}";
    }

}
