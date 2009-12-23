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

import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;

/**
 * An asynchronous update interface for receiving notifications
 * about IPool information as the IPool is constructed.
 */
public interface IPoolObserver {

    /**
     * This method is called when an Pool Object is added
     * 
     * @param o
     *            the observer
     */
    public void addUpdate(PoolObservable o);

    /**
     * This method is called when an Pool Object is removed
     * 
     * @param o
     *            the observer
     */
    public void delUpdate(PoolObservable o);

    /**
     * This method is called when an Pool Object is update
     * 
     * @param observer
     *            the observer
     * @param m
     *            the update message
     */
    public void modUpdate(PoolObservable o, NotifyMessage m);

}
