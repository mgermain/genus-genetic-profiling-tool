package edu.udes.bio.genus.client.rna;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RNAss extends AbstractCollection<Nucleotide> implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private ArrayList<Nucleotide> m_chain;

    public RNAss() {
        this.m_chain = new ArrayList<Nucleotide>();
    }

    public RNAss(String input) throws RNAException {
        this();
        fromDotParentheses(input);
    }

    public RNAss(String input, String GACU) throws RNAException {
        this(input);
        setSequence(GACU);
    }

    @Override
    public int size() {
        if (this.m_chain != null) {
            return this.m_chain.size();
        }
        return 0;
    }

    @Override
    public Iterator<Nucleotide> iterator() {
        if (this.m_chain != null) {
            return this.m_chain.iterator();
        }
        return null;
    }

    public String getDotParentheses() {
        String result = "";
        for (final Nucleotide cur : this.m_chain) {
            if (cur.m_linked == null) {
                result += ".";
            } else if (cur.m_linked_previus) {
                result += ")";
            } else {
                result += "(";
            }
        }
        return result;
    }

    public void setDotParentesis(String dotParentesis) throws RNAException {
        validateDotParentesese(dotParentesis);
        this.m_chain.clear();
        fromDotParentheses(dotParentesis);
    }

    public String getSequence() {
        String result = "";
        for (final Nucleotide n : this.m_chain) {
            result += n.m_ribose;
        }
        return result;
    }

    public boolean validateSequence(String seq) {
        return seq.matches("[GACU ]*?");
    }

    public void setSequence(String seq) throws RNAException {
        seq = seq.toUpperCase();
        if (validateSequence(seq)) {
            int i = 0;
            for (; i < seq.length() && i < this.m_chain.size(); i++) {
                this.m_chain.get(i).m_ribose = seq.charAt(i);
            }
            for (; i < this.m_chain.size(); i++) {
                this.m_chain.get(i).m_ribose = ' ';
            }
        } else {
            throw new RNAException(seq + " contains invalid characters.");
        }
    }

    private void validateDotParentesese(String structure) throws RNAException {
        int p = 0;

        if (!(structure.matches("[().]*?"))) {
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
                cur.m_linked_previus = false;
            } else if (element == ')') {
                try {
                    toBeLinked.peek().m_linked = cur;
                    cur.m_linked = toBeLinked.pop();
                    cur.m_linked_previus = true;
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            if (previus != null) {
                previus.m_next = cur;
            }
            cur.m_previus = previus;
            previus = cur;
            this.m_chain.add(cur);
        }
    }

}
