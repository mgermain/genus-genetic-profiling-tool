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

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;

/**
 * The Class Rotatation Controler.
 */
public class RotateControl extends Composite {

    /**
     * Instantiates a new rotate control.
     * 
     * @param dr
     *            the drawer
     */
    public RotateControl(Drawer dr) {
        super();
        AbsolutePanel ap;
        Button btn;
        ap = new AbsolutePanel();
        ap.setPixelSize(80, 40);
        btn = new Button();
        btn.setText("<--");
        ap.add(btn, 0, 0);
        btn = new Button();
        btn.setText("-->");
        ap.add(btn, 40, 0);
    }
}
