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

import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.rna.RNAss;

/**
 * The Class Prop_Sequences.
 */
public class Prop_Sequences extends PopupPanel {
    private String sequence = "";
    private TextBox txtName = null;
    private TextBox txtSequence = null;

    private VerticalPanel panel = new VerticalPanel();
    protected boolean updateSeq;

    private void setTextBoxName() {
        this.txtName = new TextBox();
        this.txtName.setSize("400px", "20px");
    }

    private void setTextBoxSequence() {
        this.txtSequence = new TextBox();
        this.txtSequence.setSize("400px", "20px");

        // ADD FILTER TO THE SEQUENCE TEXTBOX
        final ChangeHandler seqChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.txtSequence.getText().toUpperCase());
                if (RNAss.validateSequence(Prop_Sequences.this.txtSequence.getText())) {
                    Prop_Sequences.this.sequence = Prop_Sequences.this.txtSequence.getText();
                } else {
                    Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.sequence);
                }
            }
        };
        this.txtSequence.addChangeHandler(seqChangeHandler);

        final Set<Character> seqAllowedChars = new TreeSet<Character>();
        seqAllowedChars.add('G');
        seqAllowedChars.add('A');
        seqAllowedChars.add('C');
        seqAllowedChars.add('U');
        seqAllowedChars.add(' ');

        final KeyUpHandler seqUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (Prop_Sequences.this.updateSeq || KeyCodes.KEY_DELETE == event.getNativeKeyCode() || KeyCodes.KEY_BACKSPACE == event.getNativeKeyCode()) {
                    Prop_Sequences.this.updateSeq = false;
                    Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.txtSequence.getText().toUpperCase());

                    if (RNAss.validateSequence(Prop_Sequences.this.txtSequence.getText())) {
                        Prop_Sequences.this.sequence = Prop_Sequences.this.txtSequence.getText();
                    } else {
                        Prop_Sequences.this.txtSequence.cancelKey();
                    }
                }
            }
        };
        this.txtSequence.addKeyUpHandler(seqUpHandler);

        final KeyPressHandler seqPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final Character c = Character.toUpperCase(event.getCharCode());

                if (seqAllowedChars.contains(c)) {
                    Prop_Sequences.this.updateSeq = true;
                } else {
                    Prop_Sequences.this.txtSequence.cancelKey();
                }
            }
        };
        this.txtSequence.addKeyPressHandler(seqPressHandler);
    }

    /**
     * Instantiates a new properties panel for sequences.
     */
    public Prop_Sequences() {
        center();
        setPopupPosition(getAbsoluteLeft() - (500 / 2), getAbsoluteTop() - 100);
        setAnimationEnabled(true);
        setModal(true);

        add(this.panel);

        setTextBoxName();
        setTextBoxSequence();

        // #########################################
        // Block for name panel
        final HorizontalPanel namePanel = new HorizontalPanel();
        namePanel.setWidth("500px");
        this.panel.add(namePanel);

        final Label lblName = new Label("Name");
        lblName.setSize("100px", "20px");
        namePanel.add(lblName);
        namePanel.add(this.txtName);

        // #########################################
        // Block for sequence entry
        final HorizontalPanel nucPanel = new HorizontalPanel();
        nucPanel.setSize("500px", "20px");
        this.panel.add(nucPanel);

        final Label lblNuc = new Label("Sequence");
        lblNuc.setSize("100px", "20px");
        nucPanel.add(lblNuc);
        nucPanel.add(this.txtSequence);

        // #########################################
        // Add the action button panel
        final HorizontalPanel actionButtonPanel = new HorizontalPanel();
        actionButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        actionButtonPanel.setWidth("500px");

        // CANCEL BUTTON
        final Button btnCancel = new Button("Cancel");
        final ClickHandler cancelclick = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Prop_Sequences.this.removeFromParent();
            }
        };
        btnCancel.addClickHandler(cancelclick);
        actionButtonPanel.add(btnCancel);

        // OK BUTTON
        final Button btnOk = new Button("OK");
        final ClickHandler okclick = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GenUS.mainMenu.seqMenu.addNewSequence(Prop_Sequences.this.txtName.getText(), Prop_Sequences.this.sequence);
                Prop_Sequences.this.removeFromParent();
            }
        };
        btnOk.addClickHandler(okclick);
        actionButtonPanel.add(btnOk);

        this.panel.add(actionButtonPanel);

    }
}
