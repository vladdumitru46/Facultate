package com.example.interfaces;

import com.example.Entity;

public interface IRepository<E extends Entity<ID>, ID> {

    E add(E entity);

    E delete(ID id);

    E update(E entity);

    E findOne(ID id);

    Iterable<E> findAll();
}
