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
package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.StackPanel;

/**
 * The Class Menu.
 */
public class Menu extends HorizontalPanel {

    private final StackPanel menu = new StackPanel();

    /** The sequences menu. */
    final public Menu_Sequences seqMenu = new Menu_Sequences();

    /** The structures menu. */
    final public Menu_Structures structMenu = new Menu_Structures();

    /**
     * Instantiates a new general menu.
     */
    public Menu() {
        super();

        setSize("220px", "100%");

        // create the menu with a stack panel
        this.menu.setSize("100%", "100%");//
        add(this.menu);

        // Creating the RNA STRANDS sections
        final Menu_Strands strandMenu = new Menu_Strands();
        this.menu.add(strandMenu, "RNA STRANDS");

        // Add the CONFIG sub menu
        this.menu.add(this.seqMenu, "SEQUENCES");

        // Add the CONFIG sub menu
        this.menu.add(this.structMenu, "STRUCTURES");

        // Add the CONFIG sub menu
        final Menu_Algo algoMenu = new Menu_Algo(195);
        this.menu.add(algoMenu, "ALGORITHMS");

        // // Add the CONFIG sub menu
        // final Menu_Configs configMenu = new Menu_Configs();
        // this.menu.add(configMenu, "CONFIGS");

        // Create and setup the hide button
        final CustomButton btnHide = new CustomButton("<<", ">>") {
            @Override
            protected void onClick() {
                super.onClick();
                Menu.this.menu.setVisible(isDown());
                setDown(!isDown());
                getParent().setSize("20px", "100%");
            }
        };
        btnHide.setSize("19px", "19px");
        DOM.setStyleAttribute(btnHide.getElement(), "border", "1px solid lightgray");
        DOM.setStyleAttribute(btnHide.getElement(), "backgroundColor", "#e3e8f3");
        add(btnHide);
    }

}
