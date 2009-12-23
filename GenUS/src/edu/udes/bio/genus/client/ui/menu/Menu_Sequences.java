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
 * The Class Menu_Sequences.
 */
public class Menu_Sequences extends VerticalPanel {

    /** The sequences list. */
    public final List<Sequence> seqList = new ArrayList<Sequence>();

    /**
     * Instantiates a new sequences menu.
     */
    public Menu_Sequences() {
        super();

        // Setup base panel
        setWidth("195px");
        setBorderWidth(0);
        setSpacing(2);
        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

        // Create and setup button
        class AddRnaStrandButtonClick implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {
                final Prop_Sequences p = new Prop_Sequences();
                p.show();
            }
        }
        final Button btnAddStrands = new Button("Add Sequence", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);
    }

    /**
     * Adds the new sequence.
     * 
     * @param name
     *            the name of the new sequence. It will displayed in the menu
     * @param seq
     *            the sequence itself (ex: "GAC UGA")
     */
    public void addNewSequence(String name, String seq) {
        final Sequence s = new Sequence(name, seq);
        add(s);
        this.seqList.add(s);
    }

    /**
     * Removes the sequence.
     * 
     * @param seq
     *            the object sequence
     */
    public void removeSequence(Sequence seq) {
        remove(seq);
        this.seqList.remove(seq);
    }
}
