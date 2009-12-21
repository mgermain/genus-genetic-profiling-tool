package edu.udes.bio.genus.client.algo.match;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.WrapperRNA;
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
        final int match = 0;

        while (true) {
            // try {
            // Thread.sleep(5000L);
            // } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            System.out.println("jjkjkjkj");
        }

        /*
         * if (this.structs != null && this.structs.length == 1) {
         * this.structsResult[0] = new WrapperRNA();
         * this.structsResult[0].rna = this.structs[0];
         * for (final Nucleotide n : this.structsResult[0].rna) {
         * if (n.m_linked_previus) {
         * switch (n.m_linked.m_ribose) {
         * case 'G':
         * if (n.m_ribose != 'C') {
         * n.m_ribose = ' ';
         * }
         * break;
         * case 'A':
         * if (n.m_ribose != 'U') {
         * n.m_ribose = ' ';
         * }
         * break;
         * case 'C':
         * if (n.m_ribose != 'G') {
         * n.m_ribose = ' ';
         * }
         * break;
         * case 'U':
         * if (n.m_ribose != 'A') {
         * n.m_ribose = ' ';
         * }
         * break;
         * }
         * }
         * if (n.m_ribose != ' ') {
         * match++;
         * }
         * }
         * this.structsResult[0].match = match;
         * }
         */

    }
}
