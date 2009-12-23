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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;
import com.objetdirect.tatami.client.gfx.Color;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.rna.RNAException;
import edu.udes.bio.genus.client.rna.RNAIncompleteException;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The Class Prop_Strands.
 */
public class Prop_Strands extends AbsolutePanel {

    private boolean updateStruct = false;
    private boolean updateName = false;
    private boolean updateSeq = false;

    private TextBox txtName = null;
    private TextBox txtStructure = null;
    private TextBox txtSequence = null;
    private ListBox lbColor = null;
    private ListBox lbStyle = null;
    private CustomButton btnHide = null;

    private VerticalPanel basePropertiesPanel = null;
    private RNAssDrawable rnass;

    /**
     * The Interface PropImageBundle.
     */
    public interface PropImageBundle extends ClientBundle {

        /**
         * Hide button icon.
         * 
         * @return the image resource
         */
        @Source("hide.png")
        public ImageResource hideButtonIcon();
    }

    private final PropImageBundle imagesBundle = GWT.create(PropImageBundle.class);

    private void setTextBoxName() {
        this.txtName = new TextBox();
        this.txtName.setSize("400px", "20px");

        // ADD AUTOUPDATER TO THE NAME TEXTBOX
        final ChangeHandler nameChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setName(Prop_Strands.this.txtName.getText());
            }
        };
        this.txtName.addChangeHandler(nameChangeHandler);

        final KeyUpHandler nameUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (Prop_Strands.this.updateName || KeyCodes.KEY_DELETE == event.getNativeKeyCode() || KeyCodes.KEY_BACKSPACE == event.getNativeKeyCode()) {
                    Prop_Strands.this.updateName = false;
                    Prop_Strands.this.rnass.setName(Prop_Strands.this.txtName.getText());
                }
            }
        };
        this.txtName.addKeyUpHandler(nameUpHandler);

        final KeyPressHandler namePressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                Prop_Strands.this.updateName = true;
            }
        };
        this.txtName.addKeyPressHandler(namePressHandler);
    }

    private void setTextBoxStructure() {
        this.txtStructure = new TextBox();
        this.txtStructure.setSize("400px", "20px");

        final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
        for (final Structure s : GenUS.mainMenu.structMenu.structList) {
            oracle.add(s.structure);
        }

        final Callback cb = new Callback() {
            @Override
            public void onSuggestionsReady(Request request, Response response) {
            // TODO Auto-generated method stub

            }
        };

        // ADD FILTER TO THE Structure TEXTBOX
        final ChangeHandler dpChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                try {
                    Prop_Strands.this.rnass.setRNAssDotParentheses(Prop_Strands.this.txtStructure.getText());
                } catch (final RNAException e) {
                    Prop_Strands.this.txtStructure.setText(Prop_Strands.this.rnass.getDotParentesis());
                }
            }
        };
        this.txtStructure.addChangeHandler(dpChangeHandler);

        final Set<Character> strucAllowedChars = new TreeSet<Character>();
        strucAllowedChars.add('.');
        strucAllowedChars.add('(');
        strucAllowedChars.add(')');

        final KeyUpHandler structUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (Prop_Strands.this.updateStruct || KeyCodes.KEY_DELETE == event.getNativeKeyCode() || KeyCodes.KEY_BACKSPACE == event.getNativeKeyCode()) {
                    Prop_Strands.this.updateStruct = false;
                    try {
                        Prop_Strands.this.rnass.setRNAssDotParentheses(Prop_Strands.this.txtStructure.getText());
                    } catch (final RNAIncompleteException e) {

                    } catch (final RNAException e) {
                        Prop_Strands.this.txtStructure.cancelKey();
                    }
                }

                oracle.requestSuggestions(new Request(Prop_Strands.this.txtStructure.getText()), cb);
            }
        };
        this.txtStructure.addKeyUpHandler(structUpHandler);

        final KeyPressHandler structPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final Character c = event.getCharCode();

                if (strucAllowedChars.contains(c)) {
                    Prop_Strands.this.updateStruct = true;
                } else {
                    Prop_Strands.this.txtStructure.cancelKey();
                }
            }
        };
        this.txtStructure.addKeyPressHandler(structPressHandler);
    }

    private void setTextBoxSequence() {
        this.txtSequence = new TextBox();
        this.txtSequence.setSize("400px", "20px");

        // ADD FILTER TO THE SEQUENCE TEXTBOX
        final ChangeHandler seqChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                try {
                    Prop_Strands.this.rnass.setRNAssSequence(Prop_Strands.this.txtSequence.getText());
                } catch (final RNAException e) {
                    Prop_Strands.this.txtSequence.setText(Prop_Strands.this.rnass.getSequence());
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
                if (Prop_Strands.this.updateSeq || KeyCodes.KEY_DELETE == event.getNativeKeyCode() || KeyCodes.KEY_BACKSPACE == event.getNativeKeyCode()) {
                    Prop_Strands.this.updateSeq = false;
                    Prop_Strands.this.txtSequence.setText(Prop_Strands.this.txtSequence.getText().toUpperCase());
                    try {
                        Prop_Strands.this.rnass.setRNAssSequence(Prop_Strands.this.txtSequence.getText());
                    } catch (final RNAException e) {
                        Prop_Strands.this.txtSequence.cancelKey();
                    }
                }//
            }
        };
        this.txtSequence.addKeyUpHandler(seqUpHandler);

        final KeyPressHandler seqPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final Character c = Character.toUpperCase(event.getCharCode());

                if (seqAllowedChars.contains(c)) {
                    Prop_Strands.this.updateSeq = true;
                } else {
                    Prop_Strands.this.txtSequence.cancelKey();
                }
            }
        };
        this.txtSequence.addKeyPressHandler(seqPressHandler);
    }

    private void setListBoxColor() {
        this.lbColor = new ListBox();
        this.lbColor.setSize("200px", "20px");

        this.lbColor.addItem("JADE", "#00A86B");
        this.lbColor.addItem("JAM", "#A50B5E");
        this.lbColor.addItem("GREEN SEA", "#3CB371");
        this.lbColor.addItem("UBE", "#8878C3");
        this.lbColor.addItem("RED", Color.RED.toHex());
        this.lbColor.addItem("YELLOW", Color.YELLOW.toHex());
        this.lbColor.addItem("GREEN", Color.GREEN.toHex());
        this.lbColor.addItem("AQUA", Color.AQUA.toHex());
        this.lbColor.addItem("OLIVE", Color.OLIVE.toHex());
        this.lbColor.addItem("TEAL", Color.TEAL.toHex());
        this.lbColor.addItem("BLACK", Color.BLACK.toHex());
        this.lbColor.addItem("FUCHSIA", Color.FUCHSIA.toHex());

        final ChangeHandler colorChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setColor(Color.getColor(Prop_Strands.this.lbColor.getValue(Prop_Strands.this.lbColor.getSelectedIndex())));
            }
        };
        this.lbColor.addChangeHandler(colorChangeHandler);
    }

    private void setListBoxStyle() {
        this.lbStyle = new ListBox();
        this.lbStyle.setSize("200px", "20px");

        for (final RNAssDrawable.DrawStyle r : RNAssDrawable.DrawStyle.values()) {
            this.lbStyle.addItem(r.name().replace("_", " "), r.name());
        }

        final ChangeHandler styleChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setDrawStyle(RNAssDrawable.DrawStyle.valueOf(Prop_Strands.this.lbStyle.getValue(Prop_Strands.this.lbStyle.getSelectedIndex())));
            }
        };
        this.lbStyle.addChangeHandler(styleChangeHandler);
    }

    private void init() {
        clear();
        this.basePropertiesPanel = new VerticalPanel();
        this.basePropertiesPanel.setBorderWidth(0);
        this.basePropertiesPanel.setSize("500px", "125px");
        this.add(this.basePropertiesPanel);

        setTextBoxName();
        setTextBoxStructure();
        setTextBoxSequence();
        setListBoxColor();
        setListBoxStyle();
        setListBoxStyle();

        // #########################################
        // Block for name panel
        final HorizontalPanel namePanel = new HorizontalPanel();
        namePanel.setWidth("500px");
        this.basePropertiesPanel.add(namePanel);

        final Label lblName = new Label("Name");
        lblName.setSize("100px", "20px");
        namePanel.add(lblName);
        namePanel.add(this.txtName);

        // #########################################
        // Block for dp entry
        final HorizontalPanel dpPanel = new HorizontalPanel();
        dpPanel.setWidth("500px");
        this.basePropertiesPanel.add(dpPanel);

        final Label lblDp = new Label("Structure");
        lblDp.setTitle("DotParentesis");
        lblDp.setSize("100px", "20px");
        dpPanel.add(lblDp);
        dpPanel.add(this.txtStructure);

        // #########################################
        // Block for sequence entry
        final HorizontalPanel nucPanel = new HorizontalPanel();
        nucPanel.setSize("500px", "20px");
        this.basePropertiesPanel.add(nucPanel);

        final Label lblNuc = new Label("Sequence");
        lblNuc.setSize("100px", "20px");
        nucPanel.add(lblNuc);
        nucPanel.add(this.txtSequence);

        // #########################################
        // Dropdown for color type
        final HorizontalPanel oPanel = new HorizontalPanel();
        oPanel.setSize("500px", "20px");
        this.basePropertiesPanel.add(oPanel);

        final Label lblCol = new Label("Colors :");
        lblCol.setSize("50px", "20px");
        oPanel.add(lblCol);
        oPanel.add(this.lbColor);

        // #########################################
        // Add the style list
        final Label lblStyle = new Label("Style :");
        lblStyle.setSize("50px", "20px");
        oPanel.add(lblStyle);
        oPanel.add(this.lbStyle);

        // #########################################
        // Add the action button panel
        final HorizontalPanel actionButtonPanel = new HorizontalPanel();
        actionButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        actionButtonPanel.setWidth("500px");

        // CANCEL BUTTON
        final Image iH = new Image(this.imagesBundle.hideButtonIcon());
        this.btnHide = new CustomButton(iH) {
            @Override
            protected void onClick() {
                getParent().getParent().setVisible(false);
            }
        };
        actionButtonPanel.add(this.btnHide);

        this.basePropertiesPanel.add(actionButtonPanel);

        DOM.setStyleAttribute(getElement(), "border", "1px solid grey");
        DOM.setStyleAttribute(getElement(), "background", "#e3e8f3");

        this.setVisible(true);
    }

    /**
     * Show the area.
     */
    public void show() {
        init();

        try {
            this.rnass = new RNAssDrawable(GenUS.displayArea);
        } catch (final RNAException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        GenUS.rnaPool.addToPool(this.rnass);

        this.txtName.setFocus(true);

    };

    /**
     * Show.
     * 
     * @param rnassdrawable
     *            show properties of that object
     * 
     */
    public void show(final RNAssDrawable rnassdrawable) {
        init();
        this.rnass = rnassdrawable;
        this.txtName.setText(rnassdrawable.getName());
        this.txtStructure.setText(rnassdrawable.getDotParentesis());
        this.txtSequence.setText(rnassdrawable.getSequence());

        for (int i = 0; i < this.lbColor.getItemCount(); i++) {
            if (this.lbColor.getValue(i).equals(rnassdrawable.getColor().toHex())) {
                this.lbColor.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < this.lbStyle.getItemCount(); i++) {
            if (this.lbStyle.getValue(i).equals(rnassdrawable.getDrawStyle().name())) {
                this.lbStyle.setSelectedIndex(i);
                break;
            }
        }

        this.txtName.setFocus(true);
    }

}
