package com.example.interfaces;

import com.example.Boss;

public interface IRepoBoss extends IRepository<Boss, Integer> {

    Boss findByEmailAndPassword(String email, String password);
}
