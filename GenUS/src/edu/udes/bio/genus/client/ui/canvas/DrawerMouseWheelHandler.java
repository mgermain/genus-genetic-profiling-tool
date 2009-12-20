package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class DrawerMouseWheelHandler implements MouseWheelHandler {
    private final Drawer dr;
    private final DrawerListener dl;

    public DrawerMouseWheelHandler(Drawer dr, DrawerListener dl) {
        this.dr = dr;
        this.dl = dl;
    }

    @Override
    public void onMouseWheel(MouseWheelEvent event) {
        if (this.dl.isHolding() == true && this.dl.rna != null) {
            if (event.isSouth()) {
                this.dl.rna.rotate((float) 10.0);
            } else if (event.isNorth()) {
                this.dl.rna.rotate((float) -10.0);
            }
        } else {
            if (event.isSouth()) {
                ZoomUtil.doZoom(this.dr, ZoomUtil.zoomAction.zoomOut);
            } else if (event.isNorth()) {
                ZoomUtil.doZoom(this.dr, ZoomUtil.zoomAction.zoomIn);
            }
        }
    }

}
