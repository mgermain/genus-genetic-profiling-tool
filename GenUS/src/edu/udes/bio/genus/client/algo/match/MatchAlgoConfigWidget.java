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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import edu.udes.bio.genus.client.algo.AbsAlgoWidget;

/**
 * The Class MatchAlgoConfigWidget.
 */
public class MatchAlgoConfigWidget extends AbsAlgoWidget {
    private final static String DESCRIPTION = "This algorithm must be call with two parameters: 1-secondary structure, 2-primary sequence. It will return a secondary structure with the primary sequence set, where it is possible.";
    private final static String NAME = "Match Algorithm";

    private final MatchDialog dialog;

    /** is started. */
    public boolean started;

    /** The image container. */
    public SimplePanel imageContainer;

    /** The images bundle. */
    public MatchAlgoImageBundle imagesBundle;

    /**
     * The Interface MatchAlgoImageBundle.
     */
    public interface MatchAlgoImageBundle extends ClientBundle {

        /**
         * Details button icon.
         * 
         * @return the image resource
         */
        @Source("details.gif")
        public ImageResource detailsButtonIcon();

        /**
         * Cancel button icon.
         * 
         * @return the image resource
         */
        @Source("redX.png")
        public ImageResource cancelButtonIcon();

        /**
         * Play button icon.
         * 
         * @return the image resource
         */
        @Source("play.png")
        public ImageResource playButtonIcon();

        /**
         * Pause button icon.
         * 
         * @return the image resource
         */
        @Source("pause.png")
        public ImageResource pauseButtonIcon();

        /**
         * Check button icon.
         * 
         * @return the image resource
         */
        @Source("check.png")
        public ImageResource checkButtonIcon();
    }

    /**
     * Instantiates a new match algo config widget.
     * 
     * @param pxWidth
     *            the width in px of the dialog
     */
    public MatchAlgoConfigWidget(int pxWidth) {
        HorizontalPanel hp;
        Image img;
        this.imagesBundle = GWT.create(MatchAlgoImageBundle.class);
        hp = new HorizontalPanel();
        hp.setWidth(pxWidth + "px");
        this.started = false;
        hp.add(new Label(MatchAlgoConfigWidget.NAME));
        this.imageContainer = new SimplePanel();
        hp.add(this.imageContainer);
        img = new Image(this.imagesBundle.playButtonIcon());
        img.addClickHandler(new OptionClickHandler());
        this.imageContainer.add(img);
        img = new Image(this.imagesBundle.cancelButtonIcon());
        img.addClickHandler(new CancelClickHandler());
        img.setTitle("Press Ctrl to delete without confirmation.");
        hp.add(img);
        this.dialog = new MatchDialog(this, new OptionClickHandler());
        initWidget(hp);
    }

    private class CancelClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            // MatchAlgoConfigWidget.this.dialog.algoRequest.cancel();
            if (DOM.eventGetCtrlKey(DOM.eventGetCurrentEvent()) || Window.confirm("Are you sure you want to delete this algo ?")) {

                MatchAlgoConfigWidget.this.dialog.sendCancel();
                removeFromParent();
            }
        }
    }

    /**
     * The Class OptionClickHandler.
     */
    public class OptionClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            showOptions();
        }
    }

    @Override
    public void showOptions() {
        this.dialog.center();
        this.dialog.show();
    }

    @Override
    public String getDescription() {
        return MatchAlgoConfigWidget.NAME;
    }

    @Override
    public String getName() {
        return MatchAlgoConfigWidget.DESCRIPTION;
    }
}
