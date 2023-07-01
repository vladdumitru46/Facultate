package com.example;

import com.example.repository.RepoGame;
import com.example.repository.RepoUser;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private RepoUser repoUser;
    private RepoGame repoGame;
    private static Map<Integer, IServiceObserver> observerMap;

    public Service(RepoUser repoUser, RepoGame repoGame) {
        this.repoUser = repoUser;
        this.repoGame = repoGame;
        observerMap = new ConcurrentHashMap<>();
    }

    @Override
    public void logIn(String connectionString, IServiceObserver client) throws Exception {
        User user = repoUser.findOneByAlias(connectionString);
        if (user == null) {
            throw new Exception("Alias is not from a valid player");
        } else {
            observerMap.put(user.getId(), client);
        }
    }

    @Override
    public void logOut(User user) throws Exception {
        observerMap.remove(user);
    }

    public void updateGame(Game game) {
        repoGame.update(game);
    }

    public void addGame(Game game) {
        repoGame.add(game);
    }

    public User getUserByUserName(String text) {
        return repoUser.findOneByAlias(text);
    }

    public void updateUser(User user) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        repoUser.update(user);
        for (var iServiceObserver : observerMap.values()) {
            if (iServiceObserver != null) {
                executorService.execute(() -> {
                    try {
                        iServiceObserver.updateTable(user);

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });
            }
        }
        executorService.shutdown();
    }

    public List<User> getAllUsers() {
        return (List<User>) repoUser.findAll();
    }
    //fa service
    // vezi pe o problema cum ce
}
