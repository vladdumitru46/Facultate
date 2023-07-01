package com.example.repository;

import com.example.Game;
import com.example.interfaces.IRepoGame;
import com.example.utils.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Component
public class RepoGame implements IRepoGame {

    private static final Logger logger = LogManager.getLogger();

    public RepoGame(Properties properties) {

        logger.info("Initializing GameRepo {}", properties);
    }

    @Override
    public boolean findIfPlayerIsInAnActiveGame(Integer playerId) {
        logger.traceEntry("findIfPlayerIsInAnActiveGame");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.createQuery("FROM Game WHERE player1=:id or player2=:id", Game.class).
                        setParameter("id", playerId).setMaxResults(1).uniqueResult();
                transaction.commit();
                if (game != null) {
                    return true;
                }
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        logger.traceExit();
        return false;
    }

    @Override
    public List<Game> allGamesByPlayer(Integer id) {

        logger.traceEntry("add all games by player");
        List<Game> list = (List<Game>) findAll();
        List<Game> result = new ArrayList<>();
        for (var i : list) {
            if (Objects.equals(i.getPlayer1(), id) || Objects.equals(i.getPlayer2(), id)) {
                result.add(i);
            }
        }
        logger.traceExit();
        return result;
    }

    @Override
    public Game add(Game entity) {

        logger.traceEntry("add game");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Game delete(Integer integer) {
        logger.traceEntry("delete game");
        Game game = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game criteria = session.createQuery("from Game where id = :entity", Game.class)
                        .setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        logger.traceExit();
        return game;
    }

    @Override
    public Game update(Game entity) {
        logger.traceEntry("update game");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.load(Game.class, entity.getId());
                game.setWinner(entity.getWinner());
                game.setNoPositionsPlayedPlayer1(entity.getNoPositionsPlayedPlayer1());
                game.setNoPositionsPlayedPlayer2(entity.getNoPositionsPlayedPlayer2());
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Game findOne(Integer integer) {
        logger.traceEntry("find one game");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.createQuery("FROM Game WHERE id=:id", Game.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return game;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Iterable<Game> findAll() {
        logger.traceEntry("find all games");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Game> list = session.createQuery("FROM Game ", Game.class).stream().toList();
                transaction.commit();
                return list;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        logger.traceExit();
        return null;
    }
}
