package com.example;

public interface IService {
    //pui ce iti trebe in service

    void logIn(String connectionString, IServiceObserver client) throws Exception;

    void logOut(User boss) throws Exception;

    void updateUser(User user) throws Exception;

}
