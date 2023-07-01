package com.example.interfaces;

import com.example.User;

public interface IRepoUser extends IRepository<User, Integer> {

    User findOneByAlias(String alias);

}
