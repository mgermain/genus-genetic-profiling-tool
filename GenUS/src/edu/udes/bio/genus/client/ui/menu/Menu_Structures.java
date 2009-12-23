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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The Class Menu_Structures.
 */
public class Menu_Structures extends VerticalPanel {

    /** The structure list. */
    public final List<Structure> structList = new ArrayList<Structure>();

    /**
     * Instantiates a new structures menu.
     */
    public Menu_Structures() {
        super();

        // Setup base panel
        setWidth("195px");
        setBorderWidth(0);
        setSpacing(2);
        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

        // Create and setup button
        class AddRnaStrandButtonClick implements ClickHandler {

            /*
             * (non-Javadoc)
             * 
             * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
             */
            @Override
            public void onClick(ClickEvent event) {
                final Prop_Structures p = new Prop_Structures();
                p.show();
            }
        }
        final Button btnAddStrands = new Button("Add Structure", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);
    }

    /**
     * Adds a new strucure.
     * 
     * @param name
     *            the name of the new structure. It will displayed in the menu
     * @param seq
     *            the structure itself in dot parenthisis format (ex: "....((..)...)..")
     */

    public void addNewStrucure(String name, String struct) {
        final Structure s = new Structure(name, struct);
        add(s);
        this.structList.add(s);
    }

    /**
     * Removes a structure.
     * 
     * @param struct
     *            the structure to remove
     */
    public void removeStructure(Structure struct) {
        remove(struct);
        this.structList.remove(struct);
    }
}
