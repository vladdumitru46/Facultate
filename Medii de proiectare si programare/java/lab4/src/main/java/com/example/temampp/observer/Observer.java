package com.example.temampp.observer;

import com.example.temampp.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
