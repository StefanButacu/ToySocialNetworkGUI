package com.toysocialnetworkgui.service;

public class AuthAdapter implements UserAuth {
    private ThirdPartyAuth thirdPartyAuth;

    public AuthAdapter(ThirdPartyAuth thirdPartyAuth) {
        this.thirdPartyAuth = thirdPartyAuth;
    }

    @Override
    public boolean login(String username, String password) {
        return thirdPartyAuth.authenticate(username, password);
    }
}
