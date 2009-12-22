package edu.udes.bio.genus.client.rna;

import com.objetdirect.tatami.client.gfx.Circle;
import com.objetdirect.tatami.client.gfx.Text;

public class NucleotideGO extends com.objetdirect.tatami.client.gfx.VirtualGroup {

    private Text ribose;
    private Circle nucleotide;
    private int radius;

    private NucleotideGO() {
        super();
    }

    public NucleotideGO(int r, String rib) {
        this();
        this.radius = r;
        this.ribose = new Text(rib);
        this.nucleotide = new Circle(r);
        this.ribose.translate(-this.radius / 2, this.radius / 2).scale(1.5f);
        add(this.nucleotide);
        add(this.ribose);
    }
}
