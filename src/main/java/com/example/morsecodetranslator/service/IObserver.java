package com.example.morsecodetranslator.service;

/**
 * Represents an observer in the Observer Design Pattern.
 * Observers are notified of changes in the subject's state.
 */
public interface IObserver {

    /**
     * Notifies the observer about a state change in the subject.
     *
     * @param newValue the updated value or state
     */
    void update(String newValue);
}
