package com.example.repository;

import com.example.Player;
import com.example.interfaces.IRepoPlayer;
import com.example.utils.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

public class RepoPlayer implements IRepoPlayer {

    private static final Logger logger = LogManager.getLogger();

    public RepoPlayer(Properties properties) {
        logger.traceEntry("initializing RepoPlayer! {}", properties);
    }

    @Override
    public Player findOneByUserName(String userName) {

        logger.traceEntry("find player by user name");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Player player = session.createQuery("from Player where userName=:alias", Player.class).
                        setParameter("alias", userName).setMaxResults(1).uniqueResult();
                transaction.commit();
                return player;
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
    public Player add(Player entity) {
        logger.traceEntry("add players");
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
    public Player delete(Integer integer) {
        logger.traceEntry("delete player");
        Player player = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Player criteria = session.createQuery("from Player where id = :entity", Player.class)
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
        return player;
    }

    @Override
    public Player update(Player entity) {
        logger.traceEntry("update player");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Player player = session.load(Player.class, entity.getId());
                player.setName(entity.getName());
                player.setUserName(entity.getUserName());
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
    public Player findOne(Integer integer) {
        logger.traceEntry("find one player");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Player player = session.createQuery("FROM Player WHERE id=:id", Player.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return player;
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
    public Iterable<Player> findAll() {
        logger.traceEntry("find all players");
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Player> list = session.createQuery("FROM Player", Player.class).stream().toList();
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
