package edu.udes.bio.genus.client.ui.canvas;

import java.util.LinkedList;

import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class ZoomUtil {

    public static final double zoomQuotient = 0.8;

    // TODO : faire un type abstrait ou une interface a place
    private static LinkedList<Scaler> scalers;

    public enum zoomAction {
        zoomIn, zoomOut
    }

    private ZoomUtil() {

    }

    public static void doZoom(Drawer dr, zoomAction action) {
        if (action == zoomAction.zoomIn) {
            ZoomUtil.doZoomIn(dr, ZoomUtil.zoomQuotient);
        } else if (action == zoomAction.zoomOut) {
            ZoomUtil.doZoomOut(dr, ZoomUtil.zoomQuotient);
        }
    }

    public static void setZoom(Drawer dr, double quotient) {
        double newQuotient;

        newQuotient = quotient / dr.scaleFactor;

        if (quotient > dr.scaleFactor) {
            ZoomUtil.doZoomOut(dr, newQuotient);
        } else if (quotient < dr.scaleFactor) {
            ZoomUtil.doZoomIn(dr, 1.0 / newQuotient);
        }
    }

    private static void doZoomIn(Drawer dr, double quotient) {
        double newQuotient;

        newQuotient = 1.0 / quotient;

        dr.scaleFactor = dr.scaleFactor * newQuotient;
        for (final RNAssDrawable po : dr.pool.getAll()) {
            po.scale(newQuotient);
        }

        ZoomUtil.updateScalers(dr.scaleFactor);
    }

    private static void doZoomOut(Drawer dr, double quotient) {
        double newQuotient;

        newQuotient = quotient;

        dr.scaleFactor = dr.scaleFactor * newQuotient;
        for (final RNAssDrawable po : dr.pool.getAll()) {
            po.scale(newQuotient);
        }

        ZoomUtil.updateScalers(dr.scaleFactor);
    }

    public static void addScaler(Scaler sc) {
        if (ZoomUtil.scalers == null) {
            ZoomUtil.scalers = new LinkedList<Scaler>();
        }
        ZoomUtil.scalers.add(sc);
    }

    private static void updateScalers(double scale) {
        if (ZoomUtil.scalers != null) {
            for (final Scaler sc : ZoomUtil.scalers) {
                sc.trySetScale(scale);
            }
        }
    }
}
