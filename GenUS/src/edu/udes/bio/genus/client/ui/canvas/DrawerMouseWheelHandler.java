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
import com.google.gwt.event.dom.client.MouseWheelHandler;

/**
 * The Class DrawerMouseWheelHandler.
 */
public class DrawerMouseWheelHandler implements MouseWheelHandler {
    private final Drawer dr;
    private final DrawerListener dl;

    /**
     * Instantiates a new drawer mouse wheel handler.
     * 
     * @param dr
     *            the drawer
     * @param dl
     *            the listener
     */
    public DrawerMouseWheelHandler(Drawer dr, DrawerListener dl) {
        this.dr = dr;
        this.dl = dl;
    }

    @Override
    public void onMouseWheel(MouseWheelEvent event) {
        if (this.dl.isHolding() == true && this.dl.rna != null) {
            if (event.isSouth()) {
                this.dl.rna.rotate((float) 10.0);
            } else if (event.isNorth()) {
                this.dl.rna.rotate((float) -10.0);
            }
        } else {
            if (event.isSouth()) {
                ZoomUtil.doZoom(this.dr, ZoomUtil.zoomAction.zoomOut);
            } else if (event.isNorth()) {
                ZoomUtil.doZoom(this.dr, ZoomUtil.zoomAction.zoomIn);
            }
        }
    }

}
