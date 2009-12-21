package edu.udes.bio.genus.client.rna;

public class RNAException extends Exception {

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
