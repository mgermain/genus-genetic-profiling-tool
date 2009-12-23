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
package edu.udes.bio.genus.client.algo;

import java.io.Serializable;

import edu.udes.bio.genus.client.rna.RNAss;

/**
 * The Class AbsAlgorithm.
 */
public abstract class AbsAlgorithm implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    protected RNAss strands[];
    protected RNAss structs[];
    protected WrapperRNA strandsResult[];
    protected WrapperRNA structsResult[];
    protected boolean shouldStop = false;

    /**
     * Should stop.
     * 
     * @return true, if successful
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * Sets the should stop.
     * 
     * @param shouldStop
     *            the new should stop value
     */
    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    /**
     * Execute the algorithm
     */
    public abstract void execute();

    public AbsAlgorithm() {}

    /**
     * Instantiates a new abstract algorithm.
     * 
     * @param strands
     *            the array of strands which the algorithm needs
     * @param structs
     *            the array of structures which the algorithm needs
     */
    public AbsAlgorithm(RNAss strands[], RNAss structs[]) {
        this.strands = strands;
        this.structs = structs;
    }

    /**
     * Gets the algorithm result.
     * 
     * @return the strands result
     */
    public WrapperRNA[] getStrandsResult() {
        return this.strandsResult;
    }

    /**
     * Gets the algorithm result.
     * 
     * @return the structs result
     */
    public WrapperRNA[] getStructsResult() {
        return this.structsResult;
    }

    /**
     * Gets the strands.
     * 
     * @return the strands
     */
    public RNAss[] getStrands() {
        return this.strands;
    }

    /**
     * Gets the structs.
     * 
     * @return the structs
     */
    public RNAss[] getStructs() {
        return this.structs;
    }
}
