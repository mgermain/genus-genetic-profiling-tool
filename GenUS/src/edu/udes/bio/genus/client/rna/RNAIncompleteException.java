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

// TODO: Auto-generated Javadoc
/**
 * The Class RNAIncompleteException.
 * 
 * Exceptions releated to the parsing of the dot parentheses format for the instanciation af an RNAss
 */
public class RNAIncompleteException extends RNAException {

    /**
     * Instantiates a new RNA incomplete exception.
     * 
     * @param msg
     */
    public RNAIncompleteException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new RNA incomplete exception.
     */
    public RNAIncompleteException() {
        super();
    }

    /**
     * Instantiates a new RNA incomplete exception.
     * 
     * @param msg
     * @param t
     */
    public RNAIncompleteException(String msg, Throwable t) {
        super(msg, t);
    }

}
