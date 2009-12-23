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

import java.io.Serializable;

/**
 * The Class Nucleotide.
 * 
 * A nucleotide can be linked with up to 3 others nucleotides.
 * 
 * 1- with the previous one in the structure.
 * 2- with the next one in the structure.
 * 3- with any other else in the structure.
 * 
 */
public class Nucleotide implements Serializable {

    private static final long serialVersionUID = 1L;

    /** The previous nucleotide linked. */
    public Nucleotide previous;

    /** The next nucleotide linked. */
    public Nucleotide next;

    /** The other nucleotide linked. */
    public Nucleotide linked;

    /** If it linked with any other nucleotide, is it this nucleotide closer to the end of the structure. */
    public boolean isLinkedEnd;

    /**
     * The ribose of a nucleotide.
     * adenine (A), cytosine (C), guanine (G) or uracil (U)
     */
    public char ribose;

    /**
     * Instantiates a new nucleotide.
     */
    public Nucleotide() {
        this.previous = null;
        this.next = null;
        this.linked = null;
        this.ribose = ' ';
        this.isLinkedEnd = false;
    }

    /**
     * Instantiates a new nucleotide.
     * 
     * @param p
     *            the previous nucleotide
     * @param n
     *            the next nucleotide
     * @param l
     *            the other nucleotide
     */
    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l) {
        this();
        this.previous = p;
        this.next = n;
        this.linked = l;
    }

    /**
     * Instantiates a new nucleotide.
     * 
     * @param p
     *            the previous nucleotide
     * @param n
     *            the next nucleotide
     * @param l
     *            the other nucleotide
     * @param r
     *            the ribose of the nucleotide
     */
    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l, char r) {
        this(p, n, l);
        this.ribose = r;
    }

    /**
     * Instantiates a new nucleotide.
     * 
     * @param p
     *            the previous nucleotide
     * @param n
     *            the next nucleotide
     * @param l
     *            the other nucleotide
     * @param r
     *            the ribose of the nucleotide
     * @param l_p
     *            is the nucleotide closer to the end of the structure?
     */
    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l, char r, boolean l_e) {
        this(p, n, l, r);
        this.isLinkedEnd = l_e;
    }
}
