package edu.udes.bio.genus.client.algo.match;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.WrapperRNA;
import edu.udes.bio.genus.client.rna.Nucleotide;
import edu.udes.bio.genus.client.rna.RNAss;

public class MatchAlgorithm extends AbsAlgorithm {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public MatchAlgorithm() {
        super();
    }

    public MatchAlgorithm(RNAss[] strands, RNAss[] structs) {
        super(strands, structs);
    }

    // TODO : match !?
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
