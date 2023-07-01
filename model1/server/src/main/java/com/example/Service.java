package com.example;

import com.example.interfaces.IRepoClue;
import com.example.interfaces.IRepoGame;
import com.example.interfaces.IRepoGameForPlayer;
import com.example.interfaces.IRepoPlayer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private final IRepoPlayer repoPlayer;
    private final IRepoClue repoClue;

    private final IRepoGame repoGame;

    private final IRepoGameForPlayer repoGameForPlayer;

    private static Map<Integer, IServiceObserver> observerMap;

    public Service(IRepoPlayer repoPlayer, IRepoClue repoClue, IRepoGame repoGame, IRepoGameForPlayer repoGameForPlayer) {
        this.repoPlayer = repoPlayer;
        this.repoClue = repoClue;
        this.repoGame = repoGame;
        this.repoGameForPlayer = repoGameForPlayer;
        observerMap = new ConcurrentHashMap<>();
    }

    @Override
    public void logIn(String alias, IServiceObserver client) throws Exception {
        Player player = repoPlayer.findOneByAlias(alias);
        if (player == null) {
            throw new Exception("Alias is not from a valid player");
        } else {
            observerMap.put(player.getId(), client);
        }
    }

    @Override
    public void logOut(Player player, IServiceObserver client) {

    }

    @Override
    public Clue getClueByPosition(Integer positionLine, Integer positionColumn) {
        return repoClue.findOneByPosition(positionLine, positionColumn);
    }

    public List<Clue> getAllClues() {
        return (List<Clue>) repoClue.findAll();
    }

    public Clue getClueById(Integer id) {
        return repoClue.findOne(id);
    }

    public List<Game> getAllGames() {
        return (List<Game>) repoGame.findAll();
    }

    public Game addGame(Game game) {
        return repoGame.add(game);
    }

    public void updateGame(Game game) {
        System.out.println("SUNT AICI");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        repoGame.update(game);
        for (var iServiceObserver : observerMap.values()) {
            if (iServiceObserver != null) {
                executorService.execute(() -> {
                    try {
                        System.out.println("trimite update");
                        iServiceObserver.updateScores(game);

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });
            }
        }
        executorService.shutdown();
    }

    public GameForPlayer addGameForPlayer(GameForPlayer game) {
        return repoGameForPlayer.add(game);
    }
}
