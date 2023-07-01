package com.example.repository;

import com.example.Game;
import com.example.interfaces.IRepoGropi;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepoGame implements IRepoGropi {
    @Override
    public List<Game> findAllGamesForPlayer(String alias) {
        List<Game> list = new ArrayList<>();
        List<Game> oldList = (List<Game>) findAll();
        for (var i : oldList) {
            if (i.getPlayerAlias().equals(alias)) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public Game addPositionToGame(Integer id, String position) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.load(Game.class, id);
                String p = game.getPositionsOfPlayer();
                p = p + " " + position;
                game.setPositionsOfPlayer(p);
                transaction.commit();
                return game;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                return null;
            }
        }
    }

    @Override
    public Game add(Game entity) {
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
        return entity;
    }

    @Override
    public Game delete(Integer integer) {
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
        return game;
    }

    @Override
    public Game update(Game entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.load(Game.class, entity.getId());
                game.setPlayerAlias(entity.getPlayerAlias());
                game.setStartTime(entity.getStartTime());
                game.setGameTime(entity.getGameTime());
                game.setFinishTime(entity.getFinishTime());
                game.setPoOfGropi(entity.getPoOfGropi());
                game.setNoOfPoints(entity.getNoOfPoints());
                game.setPositionsOfPlayer(entity.getPositionsOfPlayer());
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return entity;
    }

    @Override
    public Game findOne(Integer integer) {
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
        return null;
    }

    @Override
    public Iterable<Game> findAll() {
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
        return null;
    }
}
