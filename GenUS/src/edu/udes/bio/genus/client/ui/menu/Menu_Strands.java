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

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.pool.IPoolObserver;
import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The Class Menu_Strands.
 */
public class Menu_Strands extends VerticalPanel implements IPoolObserver {

    private final HashMap<RNAssDrawable, Strand> strandHash = new HashMap<RNAssDrawable, Strand>();

    /**
     * Instantiates a new strands menu.
     */
    public Menu_Strands() {
        super();
        //
        // Setup base panel
        setWidth("195px");
        setBorderWidth(0);
        setSpacing(2);
        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

        // Create and setup button
        final Button btnAddStrands = new Button("Add RNA Strand", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);

        // Make the menu observe the pool of strands
        GenUS.rnaPool.subscribe(this);
    }

    /**
     * Adds a strand to menu. Those strands will be displayed in the canvas.
     * 
     * @param o
     *            the strand to add: RNAssDrawable
     */
    public void addStrandToMenu(RNAssDrawable o) {
        final Strand s = new Strand(o);
        this.strandHash.put(o, s);
        add(s);
    }

    /**
     * Delete a strand from menu.
     * 
     * @param o
     *            the strand to delete: RNAssDrawable
     */
    public void delStrandFromMenu(RNAssDrawable o) {
        remove(this.strandHash.get(o));
        this.strandHash.remove(o);
    }

    class AddRnaStrandButtonClick implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            GenUS.propMenu.show();
        }
    }

    @Override
    public void addUpdate(PoolObservable o) {
        addStrandToMenu((RNAssDrawable) o);
    }

    @Override
    public void delUpdate(PoolObservable o) {
        delStrandFromMenu((RNAssDrawable) o);
    }

    @Override
    public void modUpdate(PoolObservable o, NotifyMessage m) {
        this.strandHash.get(o).updateName();
    }

}
