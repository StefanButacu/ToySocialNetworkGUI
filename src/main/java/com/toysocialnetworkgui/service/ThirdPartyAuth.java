package com.toysocialnetworkgui.service;

public class ThirdPartyAuth {

    public boolean authenticate(String username, String password) {
        System.out.println("Google api LOGIN");
        if(username.equals("stef@gmail.com")){
            return true;
        }

        return false;
    }
}
