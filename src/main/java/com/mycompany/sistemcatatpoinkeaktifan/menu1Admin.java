/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

/**
 *
 * @author acer
 */
public class menu1Admin {
    private int account_id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public menu1Admin(int account_id, String firstname, String lastname, String username, String password) {
        this.account_id = account_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}

