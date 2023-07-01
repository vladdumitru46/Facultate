package com.example;

import java.util.List;

public interface IService {
    //pui ce iti trebe in service
    void logIn(String userName, IServiceObserver client) throws Exception;

    List<Users> getLoggedInUsers(Users users) throws Exception;

}
