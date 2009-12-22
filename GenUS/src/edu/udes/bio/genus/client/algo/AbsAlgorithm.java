package edu.udes.bio.genus.client.algo;

import java.io.Serializable;

import edu.udes.bio.genus.client.rna.RNAss;

public abstract class AbsAlgorithm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected RNAss strands[];
    protected RNAss structs[];
    protected WrapperRNA strandsResult[];
    protected WrapperRNA structsResult[];
    protected boolean shouldStop = false;

    public boolean shouldStop() {
        return this.shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public abstract void execute();

    public AbsAlgorithm() {}

    public AbsAlgorithm(RNAss strands[], RNAss structs[]) {
        this.strands = strands;
        this.structs = structs;
    }

    public WrapperRNA[] getStrandsResult() {
        return this.strandsResult;
    }

    public WrapperRNA[] getStructsResult() {
        return this.structsResult;
    }

    public RNAss[] getStrands() {
        return this.strands;
    }

    public RNAss[] getStructs() {
        return this.structs;
    }
}
