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
 * Constributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.objetdirect.tatami.client.gfx.GraphicCanvas;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.pool.IPoolObserver;
import edu.udes.bio.genus.client.pool.Pool;
import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * A composite of a TextBox and a CheckBox that optionally enables it.
 */
public class Drawer extends GraphicCanvas implements IPoolObserver {

    /** The pool. */
    public Pool pool;

    /** The drawer scale factor. */
    public double scaleFactor;

    /** The drawer listener */
    public DrawerListener dl;

    /**
     * Instantiates a new drawer.
     * 
     * @param pool
     *            the object pool
     */
    public Drawer(Pool pool) {
        super();

        this.scaleFactor = 1.0;
        this.dl = new DrawerListener(this);
        this.pool = pool;
        // Create background panel of widget
        setSize("100%", "100%");

        final DrawerMouseWheelHandler mwh = new DrawerMouseWheelHandler(this, this.dl);
        addDomHandler(mwh, MouseWheelEvent.getType());

        addGraphicObjectListener(this.dl);

        // Make the widget observe the pool of strands
        GenUS.rnaPool.subscribe(this);
    }

    @Override
    public void addUpdate(PoolObservable o) {}

    @Override
    public void delUpdate(PoolObservable o) {
        ((RNAssDrawable) o).setVisible(false);
    }

    @Override
    public void modUpdate(PoolObservable o, NotifyMessage m) {}
}
