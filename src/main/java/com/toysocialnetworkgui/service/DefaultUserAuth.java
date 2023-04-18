package com.toysocialnetworkgui.service;

import com.toysocialnetworkgui.domain.User;
import com.toysocialnetworkgui.utils.PasswordEncryptor;

public class DefaultUserAuth implements UserAuth{
    private final Facade service;

    public DefaultUserAuth(Facade service) {
        this.service = service;
    }

    @Override
    public boolean login(String username, String password) {
        User loggedUser = this.service.getUser(username);
        if (loggedUser == null || !loggedUser.getPassword().equals(PasswordEncryptor.toHexString(PasswordEncryptor.getSHA(password)))) {
            return false;
        }
        return true;
    }
}
