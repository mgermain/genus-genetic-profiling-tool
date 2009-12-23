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
package edu.udes.bio.genus.client.rna;

import com.objetdirect.tatami.client.gfx.Circle;
import com.objetdirect.tatami.client.gfx.Color;
import com.objetdirect.tatami.client.gfx.Text;

/**
 * The Class NucleotideGO.
 * 
 * The graphic object of a nucleotide.
 * 
 * @see com.objetdirect.tatami.client.gfx.VirtualGroup
 */
public class NucleotideGO extends com.objetdirect.tatami.client.gfx.VirtualGroup {

    private Text ribose;
    private Circle nucleotide;
    private int radius;

    private NucleotideGO() {
        super();
    }

    /**
     * Instantiates a new nucleotide graphic object.
     * 
     * @param radius
     *            the nucleotide radius
     * @param ribose
     *            the ribose of the nucleotide
     */
    public NucleotideGO(int radius, String ribose) {
        this();
        this.radius = radius;
        this.ribose = new Text(ribose);
        this.nucleotide = new Circle(radius);
        this.ribose.translate(-this.radius / 2, this.radius / 2).scale(1.5f);
        this.ribose.setStrokeColor(Color.WHITE);
        add(this.nucleotide);
        add(this.ribose);
    }
}
