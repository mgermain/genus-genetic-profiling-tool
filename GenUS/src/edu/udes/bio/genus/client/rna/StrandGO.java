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
package edu.udes.bio.genus.client.rna;

import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.VirtualGroup;

/**
 * The Class StrandGO.
 * 
 * The graphic object of a strand.
 * 
 * @see com.objetdirect.tatami.client.gfx.VirtualGroup
 */
public class StrandGO extends VirtualGroup {

    /** The current angle. */
    public float angle;

    /** The current scale factor. */
    public float scale;

    /**
     * Instantiates a new costum virtual group.
     * 
     * current angle is set to 0 degrees
     * current scale factor is set to 1
     * 
     * @see com.objetdirect.tatami.client.gfx.VirtualGroup
     * 
     */
    public StrandGO() {
        this.angle = 0;
        this.scale = 1;
    }

    @Override
    public GraphicObject rotate(float angle) {
        this.angle += angle;
        return super.rotate(angle);
    }

    /**
     * Sets the absolute rotation.
     * 
     * @param angle
     *            the angle
     * 
     * @return the graphic object
     */
    public GraphicObject setRotation(float angle) {
        return this.rotate(this.angle * -1 + angle);
    }

    @Override
    public GraphicObject scale(float factor) {
        this.scale *= factor;
        return super.scale(factor);
    }

    /**
     * Sets the absolute scale factor.
     * 
     * @param factor
     *            the factor
     * 
     * @return this
     */
    public GraphicObject setScaleFactor(float factor) {
        return this.scale(factor / this.scale);
    }

    /**
     * Update content graphic object to the current rotation and scale factor
     * 
     * @return this
     */
    public GraphicObject updateContent() {
        setRotation(this.angle);
        return setScaleFactor(this.scale);
    }

}
