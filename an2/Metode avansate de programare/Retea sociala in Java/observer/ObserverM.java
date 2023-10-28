package com.example.reteasocialagui.example.observer;


import com.example.reteasocialagui.example.events.Event;

public interface ObserverM<E extends Event> {
    void update(E e);
}
