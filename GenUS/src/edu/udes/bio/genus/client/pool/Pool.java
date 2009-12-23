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

import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The Class Pool.
 * Contains an array of Obervers (IPoolObserver) and and array Oservable (RNAssDrawable)
 * Observers are notify on observable even.
 */
public class Pool {

    private final ArrayList<RNAssDrawable> poolObjectList = new ArrayList<RNAssDrawable>();
    private final ArrayList<IPoolObserver> observers = new ArrayList<IPoolObserver>();

    /**
     * Adds the to pool.
     * 
     * @param po
     *            The observable object
     */
    public void addToPool(RNAssDrawable po) {
        this.poolObjectList.add(po);
        for (final IPoolObserver observer : this.observers) {
            po.addObserver(observer);
        }
        po.notifyObserversAdd();
    }

    /**
     * Removes the from pool.
     * 
     * @param po
     *            The observable object
     */
    public void removeFromPool(RNAssDrawable po) {
        this.poolObjectList.remove(po);
        po.notifyObserversDel();
    }

    /**
     * Subscribe.
     * 
     * @param o
     *            an observer
     */
    public void subscribe(IPoolObserver o) {
        this.observers.add(o);
        for (final RNAssDrawable p : this.poolObjectList) {
            p.addObserver(o);
        }
    }

    /**
     * Unsubscribe.
     * 
     * @param o
     *            an observer
     */
    public void unsubscribe(IPoolObserver o) {
        for (final RNAssDrawable p : this.poolObjectList) {
            p.removeObserver(o);
        }
        this.observers.remove(o);
    }

    /**
     * Gets the all.
     * 
     * @return array of obervable
     */
    public ArrayList<RNAssDrawable> getAll() {
        ArrayList<RNAssDrawable> res;
        res = new ArrayList<RNAssDrawable>();
        res.addAll(this.poolObjectList);
        return res;
    }

}
