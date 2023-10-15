package com.example.reteasocialagui.example.observer;


import com.example.reteasocialagui.example.events.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);
}
