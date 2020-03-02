package com.twelve.latesleeper.model;

import java.util.HashMap;

public class User {
    HashMap<String, String> user;

    public User(){
        user = new HashMap<String, String>();

        user.put("username", "");
        user.put("password", "");
        user.put("email", "");
        user.put("firstName", "");
        user.put("lastName", "");
        user.put("address", "");
        user.put("city", "");
        user.put("province", "");
        user.put("postalCode", "");
        user.put("phoneNumber", "");
    }

    public User(String username, String password, String email, String firstName, String lastName, String address, String city, String province, String postalCode, String phoneNumber) {
        user = new HashMap<String, String>();

        user.put("username", username);
        user.put("password", password);
        user.put("email", email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("address", address);
        user.put("city", city);
        user.put("province", province);
        user.put("postalCode", postalCode);
        user.put("phoneNumber", phoneNumber);
    }

    public HashMap<String, String> getUser(){
        return user;
    }
}
