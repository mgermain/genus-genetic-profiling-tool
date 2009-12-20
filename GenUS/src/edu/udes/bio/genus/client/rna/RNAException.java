package edu.udes.bio.genus.client.rna;

public class RNAException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3980051256329399757L;

    public RNAException(String msg) {
        super(msg);
    }

    public RNAException() {
        super();
    }

    public RNAException(String msg, Throwable t) {
        super(msg, t);
    }
}
