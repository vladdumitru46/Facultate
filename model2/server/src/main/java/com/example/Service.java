package com.example;

import com.example.interfaces.IRepoUser;
import com.example.interfaces.IRepoWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service implements IService {
    private final IRepoUser repoUser;
    private final IRepoWords repoWords;

    public static Map<Integer, IServiceObserver> observerMap = new ConcurrentHashMap<>();

    public Service(IRepoUser repoUser, IRepoWords repoWords) {
        this.repoUser = repoUser;
        this.repoWords = repoWords;
    }

    public Users getUserByUserName(String userName) {
        return repoUser.findOneByUserName(userName);
    }

    public Words getOneWord(Integer id) {
        return repoWords.findOne(id);
    }

    public List<Words> getAllWords() {
        return (List<Words>) repoWords.findAll();
    }

    @Override
    public void logIn(String userName, IServiceObserver client) throws Exception {
        Users users = repoUser.findOneByUserName(userName);
        if (users == null) {
            throw new Exception("UserName is not from a valid user");
        } else {
            System.out.println("PUNE IN PULA MEA");
            observerMap.put(users.getId(), client);
            System.out.println(observerMap.size());
        }
    }

    @Override
    public List<Users> getLoggedInUsers(Users users) {
        List<Users> list = (List<Users>) repoUser.findAll();
        List<Users> result = new ArrayList<>();
        for (var em : list) {
            if (observerMap.containsKey(em.getId())) {
                result.add(em);
            }
        }
        return result;
    }

    public Users getUserById(Integer key) {
        return repoUser.findOne(key);
    }

    public Map<Integer, IServiceObserver> getObserverMap() {
        return observerMap;
    }
}
