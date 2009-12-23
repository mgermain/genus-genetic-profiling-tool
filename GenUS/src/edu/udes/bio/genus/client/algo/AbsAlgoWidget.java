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
package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.ui.Composite;

/**
 * The Class AbsAlgoWidget.
 */
public abstract class AbsAlgoWidget extends Composite {
    protected final static String EMPTYSTRING = "";

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Show options.
     * display the algorithm dialog.
     */
    public abstract void showOptions();

}
