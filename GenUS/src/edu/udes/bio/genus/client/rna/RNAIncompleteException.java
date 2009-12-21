package edu.udes.bio.genus.client.rna;

public class RNAIncompleteException extends RNAException {

    public RNAIncompleteException(String msg) {
        super(msg);
    }

    public RNAIncompleteException() {
        super();
    }

    public RNAIncompleteException(String msg, Throwable t) {
        super(msg, t);
    }

}
