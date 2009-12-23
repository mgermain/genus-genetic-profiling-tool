package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.StackPanel;

public class Menu extends HorizontalPanel {

    private final StackPanel menu = new StackPanel();

    final public Menu_Sequences seqMenu = new Menu_Sequences();
    final public Menu_Structures structMenu = new Menu_Structures();

    public Menu() {
        super();

        setSize("220px", "100%");

        // create the menu with a stack panel
        this.menu.setSize("100%", "100%");//
        add(this.menu);

        // Creating the RNA STRANDS sections
        final Menu_Strands strandMenu = new Menu_Strands();
        this.menu.add(strandMenu, "RNA STRANDS");

        // Add the CONFIG sub menu
        this.menu.add(this.seqMenu, "SEQUENCES");

        // Add the CONFIG sub menu
        this.menu.add(this.structMenu, "STRUCTURES");

        // Add the CONFIG sub menu
        final MenuAlgo algoMenu = new MenuAlgo(195);
        this.menu.add(algoMenu, "ALGORITHMS");

        // // Add the CONFIG sub menu
        // final Menu_Configs configMenu = new Menu_Configs();
        // this.menu.add(configMenu, "CONFIGS");

        // Create and setup the hide button
        final CustomButton btnHide = new CustomButton("<<", ">>") {
            @Override
            protected void onClick() {
                super.onClick();
                Menu.this.menu.setVisible(isDown());
                setDown(!isDown());
                getParent().setSize("20px", "100%");
            }
        };
        btnHide.setSize("19px", "19px");
        DOM.setStyleAttribute(btnHide.getElement(), "border", "1px solid lightgray");
        DOM.setStyleAttribute(btnHide.getElement(), "backgroundColor", "#e3e8f3");
        add(btnHide);
    }

}
