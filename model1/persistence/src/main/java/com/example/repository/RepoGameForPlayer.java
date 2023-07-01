package com.example.repository;

import com.example.GameForPlayer;
import com.example.interfaces.IRepoGameForPlayer;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RepoGameForPlayer implements IRepoGameForPlayer {
    @Override
    public GameForPlayer add(GameForPlayer entity) {
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
    public GameForPlayer delete(Integer integer) {
        return null;
    }

    @Override
    public GameForPlayer update(GameForPlayer entity) {
        return null;
    }

    @Override
    public GameForPlayer findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<GameForPlayer> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<GameForPlayer> list = session.createQuery("FROM GameForPlayer", GameForPlayer.class).stream().toList();
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

    @Override
    public List<GameForPlayer> getByPlayerAlias(String alias) {
        List<GameForPlayer> newList = new ArrayList<>();
        List<GameForPlayer> oldList = (List<GameForPlayer>) findAll();
        for (var i : oldList) {
            if (Objects.equals(i.getPlayerAlias(), alias)) {
                newList.add(i);
            }
        }
        return newList;
    }
}
