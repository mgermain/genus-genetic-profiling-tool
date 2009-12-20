package edu.udes.bio.genus.client.rna;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class RNAss extends AbstractCollection<Nucleotide> implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // ne pas mettre final le arraylist sinon ca serialize pas -- Gro_
    private final ArrayList<Nucleotide> m_chain;

    public RNAss() {
        this.m_chain = new ArrayList<Nucleotide>();
    }

    public RNAss(String input) throws RNAException {
        this();
        fromDotParentheses(input);
    }

    public RNAss(String input, String GACU) throws RNAException {
        this(input);
        setGACU(GACU);
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

    public String getGACU() {
        String result = "";
        for (final Nucleotide n : this.m_chain) {
            result += n.m_ribose;
        }
        return result;
    }

    public void setGACU(String GACU) {
        int i = 0;
        for (; i < GACU.length() && i < this.m_chain.size(); i++) {
            this.m_chain.get(i).m_ribose = GACU.charAt(i);
        }
        for (; i < this.m_chain.size(); i++) {
            this.m_chain.get(i).m_ribose = ' ';
        }
    }

    private void validateDotParentesese(String structure) throws RNAException {
        int p = 0;
        for (int i = 0; i < structure.length(); i++) {
            final char c = structure.charAt(i);

            if (c != '(' && c != ')' && c != '.') {
                throw new RNAException(c + " is not a valid character.");
            }

            if (c == '(') {
                p++;
            } else if (c == ')') {
                p--;
            }

            if (p < 0) {
                throw new RNAException("Parentheses are not matching.");
            }
        }
    }

    private void fromDotParentheses(String input) throws RNAException {
        final Stack<Nucleotide> toBeLinked = new Stack<Nucleotide>();
        final char[] inputArray = input.toCharArray();
        Nucleotide cur;
        Nucleotide previus = null;
        for (int i = 0; i < inputArray.length; i++) {
            cur = new Nucleotide();
            switch (inputArray[i]) {
            case '(':
                toBeLinked.push(cur);
                cur.m_linked_previus = false;
                break;
            case ')':

                try {
                    toBeLinked.peek().m_linked = cur;
                    cur.m_linked = toBeLinked.pop();
                    cur.m_linked_previus = true;
                } catch (final Exception e) {
                    if (e instanceof EmptyStackException) {
                        throw new RNAException("Missing opening parenthisis in input string \"" + input + "\" at " + i + ".");
                    } else {
                        e.printStackTrace();
                    }
                }
                break;
            case '.':
                break;
            default:
                throw new RNAException("Input string \"" + input + "\" invalid character '" + inputArray[i] + "' at " + i + ".");
            }
            if (previus != null) {
                previus.m_next = cur;
            }
            cur.m_previus = previus;
            previus = cur;
            this.m_chain.add(cur);
        }

        if (!toBeLinked.isEmpty()) {
            throw new RNAException("Missing " + toBeLinked.size() + " closing parentheses.");
        }

    }

}
