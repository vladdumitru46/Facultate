package com.example.reteasocialagui.example.observer;

import com.example.reteasocialagui.example.events.Event;

public interface ObservableU<E extends Event> {
    void addObserverU(ObserverU<E> e);

    void removeObserverU(ObserverU<E> e);

    void notifyObserversU(E t);
}
