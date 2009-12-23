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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import edu.udes.bio.genus.client.GenUS;

public class Structure extends HorizontalPanel {

    private final Label sName = new Label();
    public String name = "";
    public String structure = "";

    public interface StrandImageBundle extends ClientBundle {
        @Source("red_x.png")
        public ImageResource cancelButtonIcon();
    }

    StrandImageBundle imagesBundle = GWT.create(StrandImageBundle.class);

    /**
     * Instantiates a new structure.
     * 
     * @param name
     *            the name
     * @param struct
     *            the structure in dot parenthisis format (ex: "..((...)..)." )
     */
    public Structure(String name, String struct) {
        super();
        if (name.equals("")) {
            this.name = struct;
        } else {
            this.name = name;
        }
        this.structure = struct;

        // Setup main panel
        DOM.setStyleAttribute(getElement(), "border", "1px solid #e3e8f3");
        setSize("90%", "20px");
        setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        // Create and setup label for the name of the strand
        this.sName.setPixelSize(164, 20);
        updateName();
        add(this.sName);

        // TODO Include the image in the project
        final Image iD = new Image(this.imagesBundle.cancelButtonIcon());
        final CustomButton btnDel = new CustomButton(iD) {
            @Override
            protected void onClick() {
                if (DOM.eventGetCtrlKey(DOM.eventGetCurrentEvent()) || Window.confirm("Are you sure you want to delete this strand ?")) {
                    GenUS.mainMenu.structMenu.removeStructure(Structure.this);
                }
            }
        };
        btnDel.setSize("16px", "16px");
        btnDel.setTitle("Press Ctrl to delete without confirmation.");
        add(btnDel);

        // Setup Widget
        final MouseOverHandler overHandler = new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                DOM.setStyleAttribute(getElement(), "backgroundColor", "#e3e8f3");
                DOM.setStyleAttribute(getElement(), "border", "1px solid lightgrey");
            }
        };
        addDomHandler(overHandler, MouseOverEvent.getType());
        final MouseOutHandler leaveHandler = new MouseOutHandler() {

            @Override
            public void onMouseOut(MouseOutEvent event) {
                DOM.setStyleAttribute(getElement(), "backgroundColor", "white");
                DOM.setStyleAttribute(getElement(), "border", "1px solid #e3e8f3");
            }
        };
        addDomHandler(leaveHandler, MouseOutEvent.getType());
    }

    public void updateName() {
        final int maxWidth = 14;
        this.sName.setTitle(this.structure);
        if (this.name.length() > maxWidth) {
            this.sName.setText(this.name.substring(0, maxWidth - 1) + "...");
        } else {
            this.sName.setText(this.name);
        }
    }
}
