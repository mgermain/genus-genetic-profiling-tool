/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Universit� de Sherbrooke
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
package edu.udes.bio.genus.client.algo.match;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import edu.udes.bio.genus.client.algo.ResolverService;
import edu.udes.bio.genus.client.algo.ResolverServiceAsync;
import edu.udes.bio.genus.client.algo.match.MatchAlgoConfigWidget.OptionClickHandler;
import edu.udes.bio.genus.client.rna.RNAException;
import edu.udes.bio.genus.client.rna.RNAss;

/**
 * The Class MatchDialog.
 */
public class MatchDialog extends DialogBox {
    protected final static String EMPTYSTRING = "";

    private final ResolverServiceAsync resolverService = GWT.create(ResolverService.class);

    private final TextBox txtStrand;
    private final TextBox txtStructure;
    private final MatchAlgoConfigWidget parent;
    private MatchAlgorithm algo;
    private final OptionClickHandler och;

    /** The current algorithm id. */
    public String currID;

    /** The algorithm request. */
    public Request algoRequest;

    /**
     * Instantiates a new match dialog.
     * 
     * @param parent
     *            the parent
     * @param och
     *            the option click handler
     */

    public MatchDialog(MatchAlgoConfigWidget parent, OptionClickHandler och) {
        Grid g;
        Button btn;

        this.och = och;
        this.parent = parent;

        g = new Grid(3, 2);
        g.setPixelSize(250, 250);

        // strands, sequence
        g.setWidget(0, 0, new Label("Strand : "));
        this.txtStrand = new TextBox();
        g.setWidget(0, 1, this.txtStrand);

        g.setWidget(1, 0, new Label("Structure : "));
        this.txtStructure = new TextBox();
        g.setWidget(1, 1, this.txtStructure);

        btn = new Button("Cancel");
        btn.addClickHandler(new CancelClickHandler());
        g.setWidget(2, 0, btn);
        btn = new Button("Start");
        btn.addClickHandler(new StartClickHandler());
        g.setWidget(2, 1, btn);

        setText("Match algorithm");
        setAnimationEnabled(true);
        setWidget(g);
    }

    private class StartClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            if (MatchDialog.this.parent.started == true) {
                Window.alert("This algo has already been started.");
            } else {
                if (MatchDialog.this.txtStrand.getValue() != MatchDialog.EMPTYSTRING && MatchDialog.this.txtStructure.getValue() != MatchDialog.EMPTYSTRING) {

                    hide();
                    MatchDialog.this.resolverService.getId(new IdCallback());

                } else {
                    Window.alert("All fields must be set.");
                }
            }
        }
    }

    private class IdCallback implements AsyncCallback<String> {

        @Override
        public void onFailure(Throwable caught) {
            if (!caught.getMessage().equals("")) {
                Window.alert(caught.getMessage());
            } else {
                Window.alert("Server not found.");
            }
        }

        @Override
        public void onSuccess(String result) {
            Image img;

            try {
                MatchDialog.this.currID = result;
                MatchDialog.this.algo = new MatchAlgorithm(null, new RNAss[] { new RNAss(MatchDialog.this.txtStructure.getValue(), MatchDialog.this.txtStrand.getValue()) });
                MatchDialog.this.algoRequest = MatchDialog.this.resolverService.startAlgo(MatchDialog.this.currID, MatchDialog.this.algo, new MatchCallback(MatchDialog.this.parent));
                if (MatchDialog.this.parent != null) {
                    MatchDialog.this.parent.started = true;
                    MatchDialog.this.parent.imageContainer.clear();
                }

                img = new Image(MatchDialog.this.parent.imagesBundle.pauseButtonIcon());
                img.addClickHandler(new PauseClickHandler());
                if (MatchDialog.this.parent != null) {
                    MatchDialog.this.parent.imageContainer.add(img);
                }
            } catch (final RNAException e) {
                Window.alert(e.getMessage());
            }
        }

    }

    private class CancelClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            hide();
            // GenUS.rnaPool.removeFromPool(Strand.this.poolObj);

            sendCancel();
        }
    }

    private class PauseClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            sendCancel();

        }
    }

    /**
     * Send cancel.
     */
    public void sendCancel() {
        Image img;

        if (this.parent.started == true) {
            this.algoRequest.cancel();
        }
        if (this.parent != null) {
            MatchDialog.this.parent.imageContainer.clear();
        }
        img = new Image(MatchDialog.this.parent.imagesBundle.playButtonIcon());// Image("Red_X.png");
        img.addClickHandler(MatchDialog.this.och);
        if (this.parent != null) {
            MatchDialog.this.parent.imageContainer.add(img);
        }

        if (this.parent.started == true) {
            this.resolverService.stopAlgo(this.currID, new CancelCallback());
        }
    }

    private class CancelCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {
            Window.alert(caught.getMessage());
        }

        @Override
        public void onSuccess(Void result) {}
    }
}
