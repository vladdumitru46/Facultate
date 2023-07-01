package com.example;

import com.example.interfaces.IRepoGame;
import com.example.interfaces.IRepoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private final IRepoPlayer repoPlayer;
    private final IRepoGame repoGame;
    private static Map<Integer, IServiceObserver> observerMap;

    private static List<String> positions = new ArrayList<>();

    public Service(IRepoPlayer repoPlayer, IRepoGame repoGame) {
        this.repoPlayer = repoPlayer;
        this.repoGame = repoGame;
        observerMap = new ConcurrentHashMap<>();
    }

    @Override
    public void logIn(String connectionString, IServiceObserver client) throws Exception {
        Player player = repoPlayer.findOneByUserName(connectionString);
        if (player == null) {
            throw new Exception("Alias is not from a valid player");
        } else {
            observerMap.put(player.getId(), client);
        }
    }

    @Override
    public List<Player> getLoggedInPlayers(Player player) throws Exception {
        List<Player> list = (List<Player>) repoPlayer.findAll();
        List<Player> result = new ArrayList<>();
        for (var em : list) {
            if (observerMap.containsKey(em.getId())) {
                result.add(em);
            }
        }
        return result;
    }

    @Override
    public void verifyPosition(String text, Game game) {
        String position1 = String.valueOf(game.getPositionOfPlayer1Boat().charAt(0)) + game.getPositionOfPlayer1Boat().charAt(1);
        String position2 = String.valueOf(game.getPositionOfPlayer1Boat().charAt(2)) + game.getPositionOfPlayer1Boat().charAt(3);
        System.out.println("text: " + text + " p1: " + position1 + " p2: " + position2);
        if (text.equals(position1) || text.equals(position2)) {
            IServiceObserver client = observerMap.get(game.getPlayer1());
            IServiceObserver client2 = observerMap.get(game.getPlayer2());
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            executorService.execute(() -> {
                try {
                    client.nottifyDecision("Ai ghicit!", game, text);
                    client2.nottifyDecision("Te-a lovit!", game, text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            IServiceObserver client = observerMap.get(game.getPlayer1());
            IServiceObserver client2 = observerMap.get(game.getPlayer2());
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            executorService.execute(() -> {
                try {
                    client.nottifyDecision("Nu ai ghicit!", game, text);
                    client2.nottifyDecision("Nu te-a lovit!", game, text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public Player getPlayerByUserName(String text) {
        return repoPlayer.findOneByUserName(text);
    }

    public Game addGame(Game game) {
        return repoGame.add(game);
    }

    public void addPosition(String position) {
        System.out.println("INTRU AICI");
        positions.add(position);
    }

    public List<String> last2Positions(Player player) {
        List<String> result = new ArrayList<>();
        if (positions.size() >= 2) {
            for (int i = positions.size() - 2; i < positions.size(); i++) {
                result.add(positions.get(i));
            }
        }
        return result;
    }

    public Game updateGame(Game game) {
        return repoGame.update(game);
    }

    public Player getPlayerById(Integer player2) {
        return repoPlayer.findOne(player2);
    }
}
