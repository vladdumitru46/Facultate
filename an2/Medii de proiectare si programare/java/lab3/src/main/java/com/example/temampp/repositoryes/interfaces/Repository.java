package com.example.temampp.repositoryes.interfaces;

import com.example.temampp.domains.Entity;

public interface Repository<ID, E extends Entity<ID>> {

    E findOne(ID id);

    Iterable<E> findAll();

    E add(E entity);

    E delete(ID entity);

    E update(E entity);
}
