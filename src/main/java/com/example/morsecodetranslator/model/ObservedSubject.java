package com.example.morsecodetranslator.model;

import com.example.morsecodetranslator.service.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a subject in the Observer design pattern.
 * Manages a list of observers and notifies them when the state changes.
 */
public class ObservedSubject {

    /**
     * List of observers that are notified when the state changes.
     */
    private final List<IObserver> observers = new ArrayList<>();

    /**
     * The current state of the subject.
     */
    private String state = "";

    /**
     * Adds an observer to the list.
     *
     * @param o the observer to add
     */
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    /**
     * Removes an observer from the list.
     *
     * @param o the observer to remove
     */
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    /**
     * Updates the state and notifies all observers.
     *
     * @param newState the new state to set
     */
    public void setState(String newState) {
        this.state = newState;
        notifyObservers();
    }

    /**
     * Returns the current state.
     *
     * @return the current state
     */
    public String getState() {
        return state;
    }

    /**
     * Notifies all observers of the current state.
     */
    private void notifyObservers() {
        for (IObserver o : observers) {
            o.update(state);
        }
    }
}
