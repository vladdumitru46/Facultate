package com.example.reteasocialagui.example.repository.memory;

import com.example.reteasocialagui.example.domain.Entity;
import com.example.reteasocialagui.example.domain.validators.Validator;
import com.example.reteasocialagui.example.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {

    private final Validator<E> validator;
    Map<ID, E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public E findOneByName(String firstName, String secondName) {
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public Iterable<E> findAllUsersWithOddId() {
        Map<ID, E> list = new HashMap<>();
        for (int i = 1; i < entities.size(); i += 2) {
            list.put(entities.get(i).getId(), entities.get(i));
        }
        return list.values();
    }

    @Override
    public Iterable<E> findAllUsersWithEvenId() {
        Map<ID, E> list = new HashMap<>();
        for (int i = 0; i < entities.size(); i += 2) {
            list.put(entities.get(i).getId(), entities.get(i));
        }
        return list.values();
    }

    @Override
    public E save(ID id, E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        validator.validate(entity);
        entity.setId(id);
        if (entities.get(entity.getId()) != null) {
            return entity;
        } else entities.put(entity.getId(), entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (entities.get(id) == null) {
            throw new IllegalArgumentException("Nu exista utlizator cu id-ul selectat!\n");
        }
        E u = entities.get(id);
        entities.remove(u.getId());
        return u;
    }

    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        validator.validate(entity);

        entities.put(entity.getId(), entity);

        if (entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return null;
        }
        return entity;
    }

    @Override
    public E findONeByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public Map<ID, E> get_all() {
        return entities;
    }


}
