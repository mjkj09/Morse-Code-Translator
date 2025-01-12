package com.example.morsecodetranslator.model;

import com.example.morsecodetranslator.service.IObserver;

import java.util.ArrayList;
import java.util.List;

public class ObservedSubject {
    private final List<IObserver> observers = new ArrayList<>();

    private String state = "";

    public void addObserver(IObserver o) {
        observers.add(o);
    }

    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    public void setState(String newState) {
        this.state = newState;
        notifyObservers();
    }

    public String getState() {
        return state;
    }

    private void notifyObservers() {
        for (IObserver o : observers) {
            o.update(state);
        }
    }
}
