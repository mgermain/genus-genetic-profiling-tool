package edu.udes.bio.genus.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import edu.udes.bio.genus.client.pool.Pool;
import edu.udes.bio.genus.client.rna.RNAssDrawable;
import edu.udes.bio.genus.client.rna.RNAssDrawable.DrawStyle;
import edu.udes.bio.genus.client.ui.canvas.Drawer;
import edu.udes.bio.genus.client.ui.canvas.Scaler;
import edu.udes.bio.genus.client.ui.menu.Menu;
import edu.udes.bio.genus.client.ui.menu.Prop_Strands;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GenUS extends AbsolutePanel implements EntryPoint {

    // Creating the pool of strands to draw
    public static Pool rnaPool = new Pool();

    public static Drawer displayArea = new Drawer(GenUS.rnaPool);
    public static Menu mainMenu = new Menu();
    public static Prop_Strands propMenu = new Prop_Strands();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // Setup the browser window
        // Window.enableScrolling(false);
        Window.setMargin("0px");
        Window.setTitle("RNA Secondary Strand Viewer");

        // Setup the root panel
        RootPanel.get().setSize("100%", "100%");

        // Setup the background panel
        setSize("100%", "100%");
        RootPanel.get().add(this);

        // Set display area
        this.add(GenUS.displayArea, 0, 0);

        // Add the zoomer
        final Scaler zoomer = new Scaler(GenUS.displayArea);
        this.add(zoomer, Window.getClientWidth() - 21, 0);

        // Add the main menu
        this.add(GenUS.mainMenu, 0, 0);

        // Add the properties panel
        this.add(GenUS.propMenu, Window.getClientWidth() - 502, Window.getClientHeight() - 125);

        // ### TESTING : Add a strand to pool
        try {
            final RNAssDrawable testStrand = new RNAssDrawable("..((((((((......))))))))..", "ACGUGCCACGAUUCAACGUGGCACAG", GenUS.displayArea);
            testStrand.setName("TEST").scale(GenUS.displayArea.scaleFactor);

            GenUS.rnaPool.addToPool(testStrand);

            final RNAssDrawable testStrand2 = new RNAssDrawable(".(((....)..))..", "ACGUGCCACGAU", GenUS.displayArea);
            testStrand.setName("TEST2").scale(GenUS.displayArea.scaleFactor).setDrawStyle(DrawStyle.Linear_Round);

            GenUS.rnaPool.addToPool(testStrand2);

            /*
             * for (int i = 0; i < 5; i++) { RNAssDrawable testStrand2 = new RNAssDrawable("..((......))", i + "  ", displayArea); testStrand2.setName("TEST" + i).scale(displayArea.scaleFactor); testStrand2.setDrawStyle(RNAssDrawable.DrawStyle.Linear_Round);
             * 
             * rnaPool.addToPool(testStrand2); }
             */

        } catch (final Exception e) {
            Window.alert("TESTING STRAND ERROR GOTTA FIX TAHT SHIT: " + e.getMessage());
        }
    }

}
