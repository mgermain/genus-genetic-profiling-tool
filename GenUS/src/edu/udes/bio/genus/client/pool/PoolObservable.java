/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Université de Sherbrooke
 * Contact: code.google.com/p/genus-genetic-profiling-tool/
 * 
 * This is a free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the GNU
 * Lesser General Public License for more details.
 *  
 * Contributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.pool;

import java.util.ArrayList;

/**
 * The Class PoolObservable.
 */
public class PoolObservable {

    /**
     * The Enum NotifyMessage.
     * List of notifiable events
     */
    public enum NotifyMessage {
        ADD, DEL, UPDATE
    };

    private final ArrayList<IPoolObserver> observers = new ArrayList<IPoolObserver>();

    /**
     * Adds the observer.
     * 
     * @param o
     *            the observer
     */
    public void addObserver(IPoolObserver o) {
        this.observers.add(o);
    }

    /**
     * Removes the observer.
     * 
     * @param o
     *            the observer
     */
    public void removeObserver(IPoolObserver o) {
        this.observers.remove(o);
    }

    /**
     * Notify observers of an add event.
     */
    public void notifyObserversAdd() {
        for (final IPoolObserver o : this.observers) {
            o.addUpdate(this);
        }
    }

    /**
     * Notify observers of delete event.
     */
    public void notifyObserversDel() {
        for (final IPoolObserver o : this.observers) {
            o.delUpdate(this);
        }
    }

    /**
     * Notify observers of a modofication event.
     * 
     * @param m
     */
    public void notifyObserversMod(NotifyMessage m) {
        for (final IPoolObserver o : this.observers) {
            o.modUpdate(this, m);
        }
    }
}
