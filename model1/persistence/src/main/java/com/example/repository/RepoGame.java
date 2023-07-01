package com.example.repository;

import com.example.Game;
import com.example.interfaces.IRepoGame;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepoGame implements IRepoGame {
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
        return null;
    }

    @Override
    public Game update(Game entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Game game = session.load(Game.class, entity.getId());
                game.setNumberOfTries(entity.getNumberOfTries());
                game.setTextOfClue(entity.getTextOfClue());
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
        return null;
    }

    @Override
    public Iterable<Game> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Game> list = session.createQuery("FROM Game", Game.class).stream().toList();
                transaction.commit();

                List<Game> games = new ArrayList<>(list);
                games.sort(Comparator.comparingInt(Game::getNumberOfTries));
                return games;
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
