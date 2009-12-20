package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.user.client.Event;
import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.GraphicObjectListener;

import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class DrawerListener implements GraphicObjectListener {
    private boolean holding;
    private int x, y;
    private final Drawer dr;
    public RNAssDrawable rna;
    private boolean rnaSet;

    public DrawerListener(Drawer dr) {
        this.dr = dr;
        this.rnaSet = false;
        this.holding = false;
    }

    @Override
    public void mouseClicked(GraphicObject graphicObject, Event event) {
    /*
     * for (RNAssDrawable rna :this.dr.drawedObjects){ if (rna.ContainVirtualGroup(graphicObject.getGroup())) { rna.rotate(5); } }
     */
    }

    @Override
    public void mouseDblClicked(GraphicObject graphicObject, Event event) {
    // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(GraphicObject graphicObject, Event event) {
        int newX, newY, xMove, yMove;

        if (this.holding == true) {
            newX = event.getClientX();
            newY = event.getClientY();
            xMove = newX - this.x;
            yMove = newY - this.y;

            if (this.rnaSet == true) {
                this.rna.translate(xMove, yMove);
            }

            if (this.rnaSet == false) {
                // Window.alert("weee");
                reDrawAllTranslation(xMove, yMove);
            }

            this.x = newX;
            this.y = newY;
        }
    }

    @Override
    public void mousePressed(GraphicObject graphicObject, Event event) {
        this.x = event.getClientX();
        this.y = event.getClientY();

        if (graphicObject != null) {
            for (final RNAssDrawable rna : this.dr.pool.getAll()) {
                if (rna.ContainVirtualGroup(graphicObject.getGroup())) {
                    this.rna = rna;
                    this.rnaSet = true;
                }
            }
        }
        this.holding = true;
    }

    @Override
    public void mouseReleased(GraphicObject graphicObject, Event event) {
        int newX, newY, xMove, yMove;

        this.holding = false;

        newX = event.getClientX();
        newY = event.getClientY();
        xMove = newX - this.x;
        yMove = newY - this.y;

        if (this.rnaSet == true) {
            this.rna.translate(xMove, yMove);
        }

        if (this.rnaSet == false) {
            // Window.alert("weee");
            reDrawAllTranslation(xMove, yMove);
        }
        // this.reDrawAllTranslation(xMove, yMove);

        this.rna = null;
        this.rnaSet = false;
    }

    private void reDrawAllTranslation(int moveX, int moveY) {

        for (final RNAssDrawable po : this.dr.pool.getAll()) {
            po.translate(moveX, moveY);
        }
    }

    public boolean isHolding() {
        return this.holding;
    }
}
