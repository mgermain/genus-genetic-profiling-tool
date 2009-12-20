package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.ui.Composite;

public abstract class AbsAlgoWidget extends Composite {
    protected final static String EMPTYSTRING = "";

    public abstract String getName();

    public abstract String getDescription();

    public abstract void showOptions();

}
