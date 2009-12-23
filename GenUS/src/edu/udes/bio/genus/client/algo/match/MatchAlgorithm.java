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
package edu.udes.bio.genus.client.algo.match;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.WrapperRNA;
import edu.udes.bio.genus.client.rna.Nucleotide;
import edu.udes.bio.genus.client.rna.RNAss;

/**
 * The Class MatchAlgorithm.
 */
public class MatchAlgorithm extends AbsAlgorithm {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new match algorithm.
     */
    public MatchAlgorithm() {
        super();
    }

    /**
     * Instantiates a new match algorithm.
     * 
     * @param strands
     *            the input strands
     * @param structs
     *            the input structs
     */
    public MatchAlgorithm(RNAss[] strands, RNAss[] structs) {
        super(strands, structs);
    }

    @Override
    public void execute() {
        this.structsResult = new WrapperRNA[1];
        this.structsResult[0] = null;
        int match = 0;

        if (this.structs != null && this.structs.length == 1) {
            this.structsResult[0] = new WrapperRNA();
            this.structsResult[0].rna = this.structs[0];
            for (final Nucleotide n : this.structsResult[0].rna) {
                if (n.isLinkedEnd) {
                    switch (n.linked.ribose) {
                    case 'G':
                        if (n.ribose != 'C') {
                            n.ribose = ' ';
                        }
                        break;
                    case 'A':
                        if (n.ribose != 'U') {
                            n.ribose = ' ';
                        }
                        break;
                    case 'C':
                        if (n.ribose != 'G') {
                            n.ribose = ' ';
                        }
                        break;
                    case 'U':
                        if (n.ribose != 'A') {
                            n.ribose = ' ';
                        }
                        break;
                    }
                }
                if (n.ribose != ' ') {
                    match++;
                }
            }
            this.structsResult[0].match = match;
        }
    }
}
