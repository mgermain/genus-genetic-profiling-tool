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
 * Constributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.objetdirect.tatami.client.Slider;

/**
 * The Class Scaler.
 */
public class Scaler extends Composite {

    private double scales[];
    private final Slider sl;
    private final Drawer dr;

    /**
     * Instantiates a new scaler.
     * 
     * @param dr
     *            the drawer
     */
    public Scaler(Drawer dr) {
        this.dr = dr;
        this.sl = new Slider("vertical", 0, 20, 10, true);
        this.sl.addValueChangeHandler(new MyHandler());
        initscales();
        this.sl.setPixelSize(20, 200);
        ZoomUtil.addScaler(this);
        initWidget(this.sl);
        DOM.setStyleAttribute(getElement(), "border", "1px solid lightgray");
        DOM.setStyleAttribute(getElement(), "backgroundColor", "#e3e8f3");
    }

    private void initscales() {
        double scale;
        this.scales = new double[21];
        scale = 1;
        for (int i = 10; i < 21; i++) {
            this.scales[i] = scale;
            scale = scale / 0.8;
        }
        scale = 1;
        for (int i = 10; i >= 0; i--) {
            this.scales[i] = scale;
            scale = scale * 0.8;
        }
    }

    private class MyHandler implements ValueChangeHandler<Integer> {
        @Override
        public void onValueChange(ValueChangeEvent<Integer> event) {
            ZoomUtil.setZoom(Scaler.this.dr, Scaler.this.scales[event.getValue()]);
            System.out.println(event.getValue());
        }
    }

    /**
     * Set the scale if it is an acceptable range.
     * 
     * @param scale
     *            the new scale
     */
    public void trySetScale(double scale) {
        int index;
        index = -1;
        for (int i = 0; i < 21; i++) {
            if (this.scales[i] > (scale - 0.00000009) && this.scales[i] < (scale + 0.00000009)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            this.sl.setValue(index);
        }
    }
}
