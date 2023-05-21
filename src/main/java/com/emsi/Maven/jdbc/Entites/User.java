package com.emsi.Maven.jdbc.Entites;

public class User {
    int id;
    public  String login;
    public  String password;

    public User() {
    }

    public User(int id , String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
