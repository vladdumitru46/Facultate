package com.example.reteasocialagui.example.domain.validators;

import com.example.reteasocialagui.example.domain.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String msg = "";
        if (entity.getFirstName().equals(""))
            msg += "Nume invalid!\n";
        if (entity.getLastName().equals("")) {
            msg += "Prenume invalid!\n";
        }
        if (!msg.equals("")) {
            throw new ValidationException(msg);
        }
    }
}
