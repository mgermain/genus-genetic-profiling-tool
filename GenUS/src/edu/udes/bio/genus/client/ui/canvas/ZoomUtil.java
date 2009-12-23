/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Université de Sherbrooke
 * Contact: code.google.com/p/genus-genetic-profiling-tool/
 * 
 * This is a free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the GNU
 * Lesser General Public License for more details.
 *  
 * Contributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.ui.canvas;

import java.util.LinkedList;

import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The Class ZoomUtil.
 */
public class ZoomUtil {

    /** The Constant ZOOMQUOTIENT. */
    public static final double ZOOMQUOTIENT = 0.8;
    private static LinkedList<Scaler> scalers;

    /**
     * The Enum zoomAction.
     */
    public enum zoomAction {
        zoomIn, zoomOut
    }

    /**
     * Instantiates a new zoom util.
     */
    private ZoomUtil() {}

    /**
     * Zoom.
     * 
     * @param dr
     *            the drawer
     * @param action
     *            the zoom action
     */
    public static void doZoom(Drawer dr, zoomAction action) {
        if (action == zoomAction.zoomIn) {
            ZoomUtil.doZoomIn(dr, ZoomUtil.ZOOMQUOTIENT);
        } else if (action == zoomAction.zoomOut) {
            ZoomUtil.doZoomOut(dr, ZoomUtil.ZOOMQUOTIENT);
        }
    }

    /**
     * Sets the zoom.
     * 
     * @param drhe
     *            drawer
     *            t
     * @param quotient
     *            the scale factor quotient
     */
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

    /**
     * Adds the scaler to zoom util.
     * 
     * @param sc
     *            the scaler
     */
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
