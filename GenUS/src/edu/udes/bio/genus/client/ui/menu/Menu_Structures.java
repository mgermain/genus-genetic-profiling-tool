package edu.udes.bio.genus.client.ui.menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu_Structures extends VerticalPanel {

    public final List<Structure> structList = new ArrayList<Structure>();

    public Menu_Structures() {
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
                final Prop_Structures p = new Prop_Structures();
                p.show();
            }
        }
        final Button btnAddStrands = new Button("Add Structure", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);
    }

    public void addNewStrucure(String name, String struct) {
        final Structure s = new Structure(name, struct);
        add(s);
        this.structList.add(s);
    }

    public void removeStructure(Structure struct) {
        remove(struct);
        this.structList.remove(struct);
    }
}
