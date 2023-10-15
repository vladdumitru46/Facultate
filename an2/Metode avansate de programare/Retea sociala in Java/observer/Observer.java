package com.example.reteasocialagui.example.observer;


import com.example.reteasocialagui.example.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
