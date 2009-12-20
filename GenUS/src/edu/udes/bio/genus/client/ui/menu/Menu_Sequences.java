package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;

public class Menu_Sequences extends VerticalPanel {

    // private final HashMap<String, String> seqList = new HashMap<String, String>();

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
                if (GenUS.propMenu.isVisible()) {

                }
            }
        }
        final Button btnAddStrands = new Button("Add RNA Strand", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);
    }
}
