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
package edu.udes.bio.genus.client.algo.match;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.WrapperRNA;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

/**
 * The Class MatchCallback.
 */
public class MatchCallback implements AsyncCallback<AbsAlgorithm> {
    MatchAlgoConfigWidget parent;
    DialogBox db;
    CheckBox ch;
    AbsAlgorithm result;
    TextBox txtName;

    /**
     * Instantiates a new match callback.
     * 
     * @param parent
     *            the callback parent
     */
    public MatchCallback(MatchAlgoConfigWidget parent) {
        this.parent = parent;
    }

    @Override
    public void onFailure(Throwable caught) {
        // TODO should do something better here
        Window.alert(caught.getMessage());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    @Override
    public void onSuccess(AbsAlgorithm result) {
        VerticalPanel vp;
        HorizontalPanel hp;
        Image img;

        this.result = result;

        img = new Image(this.parent.imagesBundle.checkButtonIcon());// Image("Red_X.png");
        // img.addClickHandler(new CancelClickHandler());
        this.parent.imageContainer.clear();
        this.parent.imageContainer.add(img);

        this.db = new DialogBox();
        vp = new VerticalPanel();

        vp.add(new Label("Result : "));

        hp = new HorizontalPanel();
        hp.add(new Label("Strand : "));
        hp.add(new Label(result.getStructsResult()[0].rna.getSequence()));
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.add(new Label("Matches : "));
        hp.add(new Label("" + result.getStructsResult()[0].match));
        vp.add(hp);

        this.ch = new CheckBox("Add strand to pool");
        this.ch.setValue(true);
        this.ch.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                MatchCallback.this.txtName.setEnabled(MatchCallback.this.ch.getValue());
            }
        });

        vp.add(this.ch);

        hp = new HorizontalPanel();

        this.txtName = new TextBox();
        this.txtName.setText("Match Algo");
        // txtName.setEnabled(false);

        hp.add(new Label("Name : "));
        hp.add(this.txtName);

        vp.add(hp);

        vp.add(new Button("Close", new CloseHandler()));

        vp.setPixelSize(250, 250);
        this.db.add(vp);
        this.db.setPixelSize(250, 250);

        this.db.setText("Server answer");
        this.db.center();
        this.db.show();
    }

    private class CloseHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            RNAssDrawable rd;

            MatchCallback.this.db.hide();

            if (MatchCallback.this.ch.getValue() == true) {
                for (final WrapperRNA w : MatchCallback.this.result.getStructsResult()) {
                    rd = new RNAssDrawable(w.rna, GenUS.displayArea);
                    rd.setName(MatchCallback.this.txtName.getText());
                    GenUS.rnaPool.addToPool(rd);
                }
            }
        }
    }
}
