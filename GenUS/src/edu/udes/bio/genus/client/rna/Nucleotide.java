package edu.udes.bio.genus.client.rna;

import java.io.Serializable;

public class Nucleotide implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public Nucleotide m_previus;
    public Nucleotide m_next;
    public Nucleotide m_linked;
    public boolean m_linked_previus;
    public char m_ribose; // adenine (A), cytosine (C), guanine (G) or uracil (U)

    public Nucleotide() {
        this.m_previus = null;
        this.m_next = null;
        this.m_linked = null;
        this.m_ribose = ' ';
        this.m_linked_previus = false;
    }

    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l) {
        this();
        this.m_previus = p;
        this.m_next = n;
        this.m_linked = l;
    }

    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l, char r) {
        this(p, n, l);
        this.m_ribose = r;
    }

    public Nucleotide(Nucleotide p, Nucleotide n, Nucleotide l, char r, boolean l_p) {
        this(p, n, l, r);
        this.m_linked_previus = l_p;
    }
}
