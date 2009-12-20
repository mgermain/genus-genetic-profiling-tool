package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.objetdirect.tatami.client.gfx.GraphicCanvas;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.pool.IPoolObserver;
import edu.udes.bio.genus.client.pool.Pool;
import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * A composite of a TextBox and a CheckBox that optionally enables it.
 */
public class Drawer extends GraphicCanvas implements IPoolObserver {

    // public ArrayList<RNAssDrawable> drawedObjects = new ArrayList<RNAssDrawable>();
    public Pool pool;
    public double scaleFactor;
    public DrawerListener dl;

    public Drawer(Pool pool) {
        super();

        this.scaleFactor = 1.0;
        this.dl = new DrawerListener(this);
        this.pool = pool;
        // Create background panel of widget
        setSize("100%", "100%");

        final DrawerMouseWheelHandler mwh = new DrawerMouseWheelHandler(this, this.dl);
        addDomHandler(mwh, MouseWheelEvent.getType());

        addGraphicObjectListener(this.dl);

        // Make the widget observe the pool of strands
        GenUS.rnaPool.subscribe(this);
    }

    @Override
    public void addUpdate(PoolObservable o) {
    // drawedObjects.add((RNAssDrawable) o);
    }

    @Override
    public void delUpdate(PoolObservable o) {
        // drawedObjects.remove(o);
        ((RNAssDrawable) o).setVisible(false);
    }

    @Override
    public void modUpdate(PoolObservable o, NotifyMessage m) {
    // TODO ..... do someting here
    }
}
