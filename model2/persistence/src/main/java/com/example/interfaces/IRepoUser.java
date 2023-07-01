package com.example.interfaces;

import com.example.Users;

public interface IRepoUser extends IRepository<Users, Integer> {
    
    Users findOneByUserName(String userName);
}
