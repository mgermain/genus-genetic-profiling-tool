/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Universit� de Sherbrooke
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

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class RNAss.
 */
public class RNAss extends AbstractCollection<Nucleotide> implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Nucleotide> nucleotideChain;

    /**
     * Instantiates a new RNA secondary structure.
     */
    public RNAss() {
        this.nucleotideChain = new ArrayList<Nucleotide>();
    }

    /**
     * Instantiates a new RNA secondary structure.
     * 
     * @param input
     *            a dot parenthesis string input.
     * 
     * @throws RNAException
     */
    public RNAss(String input) throws RNAException {
        this();
        fromDotParentheses(input);
    }

    /**
     * Instantiates a new rN ass.
     * 
     * @param input
     *            a dot parenthesis string input.
     * @param sequence
     *            the ribose sequence
     * 
     * @throws RNAException
     */
    public RNAss(String input, String sequence) throws RNAException {
        this(input);
        setSequence(sequence);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#size()
     */
    @Override
    public int size() {
        if (this.nucleotideChain != null) {
            return this.nucleotideChain.size();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#iterator()
     */
    @Override
    public Iterator<Nucleotide> iterator() {
        if (this.nucleotideChain != null) {
            return this.nucleotideChain.iterator();
        }
        return null;
    }

    /**
     * Gets the dot parentheses string.
     * 
     * @return the dot parentheses string.
     */
    public String getDotParentheses() {
        String result = "";
        for (final Nucleotide cur : this.nucleotideChain) {
            if (cur.linked == null) {
                result += ".";
            } else if (cur.isLinkedEnd) {
                result += ")";
            } else {
                result += "(";
            }
        }
        return result;
    }

    /**
     * Sets the secondary structure from a dot parentheses string.
     * 
     * @param dotParentesis
     *            the new structure
     * 
     * @throws RNAException
     */
    public void setDotParentesis(String dotParentesis) throws RNAException {
        final String seq = getSequence();
        RNAss.validateDotParenthisis(dotParentesis);
        this.nucleotideChain.clear();
        fromDotParentheses(dotParentesis);
        setSequence(seq);
    }

    /**
     * Gets the sequence.
     * 
     * @return the sequence
     */
    public String getSequence() {
        String result = "";
        for (final Nucleotide n : this.nucleotideChain) {
            result += n.ribose;
        }
        return result;
    }

    /**
     * Sets the sequence.
     * 
     * @param seq
     *            the new sequence
     * 
     * @throws RNAException
     */
    public void setSequence(String seq) throws RNAException {
        seq = seq.toUpperCase();
        if (RNAss.validateSequence(seq)) {
            int i = 0;
            for (; i < seq.length() && i < this.nucleotideChain.size(); i++) {
                this.nucleotideChain.get(i).ribose = seq.charAt(i);
            }
            for (; i < this.nucleotideChain.size(); i++) {
                this.nucleotideChain.get(i).ribose = ' ';
            }
        } else {
            throw new RNAException(seq + " contains invalid characters.");
        }
    }

    /**
     * Validate sequence.
     * 
     * @param seq
     *            the sequence
     * 
     * @return true, if matches [GACU ]
     */
    public static boolean validateSequence(String seq) {
        return seq.matches("(G|A|C|U|\\s)*");
        // return seq.matches("[GACU ]*?");
    }

    /**
     * Validate dot parenthesis.
     * 
     * @param structure
     * 
     * @throws RNAException
     */
    public static void validateDotParenthisis(String structure) throws RNAException {
        int p = 0;

        // if (!(structure.matches("[().]*?"))) {
        if (!(structure.matches("(\\(|\\)|\\.)*"))) {
            throw new RNAException(structure + " contains invalid characters.");
        }

        for (int i = 0; i < structure.length(); i++) {
            final char c = structure.charAt(i);
            if (c == '(') {
                p++;
            } else if (c == ')') {
                p--;
            }
        }
        if (p > 0) {
            throw new RNAIncompleteException(structure + " is missing closing parenthisis.");
        } else if (p < 0) {
            throw new RNAException(structure + " is missing opening parenthisis.");
        }
    }

    private void fromDotParentheses(String input) throws RNAException {
        final Stack<Nucleotide> toBeLinked = new Stack<Nucleotide>();
        final char[] inputArray = input.toCharArray();
        Nucleotide cur;
        Nucleotide previus = null;
        for (final char element : inputArray) {
            cur = new Nucleotide();
            if (element == '(') {
                toBeLinked.push(cur);
                cur.isLinkedEnd = false;
            } else if (element == ')') {
                try {
                    toBeLinked.peek().linked = cur;
                    cur.linked = toBeLinked.pop();
                    cur.isLinkedEnd = true;
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            if (previus != null) {
                previus.next = cur;
            }
            cur.previous = previus;
            previus = cur;
            this.nucleotideChain.add(cur);
        }
    }

}
