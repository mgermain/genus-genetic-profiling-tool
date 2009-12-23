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
package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.user.client.Event;
import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.GraphicObjectListener;

import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The listener interface for receiving drawer events.
 * 
 */
public class DrawerListener implements GraphicObjectListener {
    private boolean holding;
    private int x, y;
    private final Drawer dr;

    /** The RNAss drawable listened. */
    public RNAssDrawable rna;
    private boolean rnaSet;

    /**
     * Instantiates a new drawer listener.
     * 
     * @param dr
     *            the drawer
     */
    public DrawerListener(Drawer dr) {
        this.dr = dr;
        this.rnaSet = false;
        this.holding = false;
    }

    @Override
    public void mouseClicked(GraphicObject graphicObject, Event event) {}

    @Override
    public void mouseDblClicked(GraphicObject graphicObject, Event event) {}

    @Override
    public void mouseMoved(GraphicObject graphicObject, Event event) {
        int newX, newY, xMove, yMove;

        if (graphicObject != null) {
            this.dr.setStyleName("pointerCursor");
        } else {
            this.dr.setStyleName("moveCursor");
        }

        if (this.holding == true) {
            newX = event.getClientX();
            newY = event.getClientY();
            xMove = newX - this.x;
            yMove = newY - this.y;

            if (this.rnaSet == true) {
                this.rna.translate(xMove, yMove);
            }

            if (this.rnaSet == false) {
                reDrawAllTranslation(xMove, yMove);
            }

            this.x = newX;
            this.y = newY;
        }
    }

    @Override
    public void mousePressed(GraphicObject graphicObject, Event event) {
        this.x = event.getClientX();
        this.y = event.getClientY();

        if (graphicObject != null) {
            for (final RNAssDrawable rna : this.dr.pool.getAll()) {
                if (rna.ContainVirtualGroup(graphicObject.getGroup())) {
                    this.rna = rna;
                    this.rnaSet = true;
                }
            }
        }
        this.holding = true;
    }

    @Override
    public void mouseReleased(GraphicObject graphicObject, Event event) {
        int newX, newY, xMove, yMove;

        this.holding = false;

        newX = event.getClientX();
        newY = event.getClientY();
        xMove = newX - this.x;
        yMove = newY - this.y;

        if (this.rnaSet == true) {
            this.rna.translate(xMove, yMove);
        }

        if (this.rnaSet == false) {
            reDrawAllTranslation(xMove, yMove);
        }
        this.rna = null;
        this.rnaSet = false;
    }

    private void reDrawAllTranslation(int moveX, int moveY) {
        for (final RNAssDrawable po : this.dr.pool.getAll()) {
            po.translate(moveX, moveY);
        }
    }

    /**
     * Checks if is holding.
     * 
     * @return true, if is holding
     */
    public boolean isHolding() {
        return this.holding;
    }
}
