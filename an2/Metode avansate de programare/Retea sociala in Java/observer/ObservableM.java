package com.example.reteasocialagui.example.observer;


import com.example.reteasocialagui.example.events.Event;

public interface ObservableM<E extends Event> {
    void addObserverM(ObserverM<E> e);

    void removeObserverM(ObserverM<E> e);

    void notifyObserversM(E t);
}
