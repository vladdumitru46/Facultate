package com.example.reteasocialagui.example.repository.file;

import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.Validator;


import java.util.List;

public class UserFile extends AbstractFileRepository<Integer, User> {

    public UserFile(String fileName, Validator<User> validator) {
        super(fileName, validator);
    }

    @Override
    public User extractEntity(List<String> attributes) {
        User user = new User(attributes.get(1), attributes.get(2));
        user.setId(Integer.parseInt(attributes.get(0)));

        return user;
    }

    @Override
    protected String createEntityAsString(User entity) {
        return entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName();
    }

}
