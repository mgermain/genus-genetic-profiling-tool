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

    public final List<Sequence> seqList = new ArrayList<Sequence>();

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
                final Prop_Sequences p = new Prop_Sequences();
                p.show();
            }
        }
        final Button btnAddStrands = new Button("Add Sequence", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);
    }

    public void addNewSequence(String name, String seq) {
        final Sequence s = new Sequence(name, seq);
        add(s);
        this.seqList.add(s);
    }

    public void removeSequence(Sequence seq) {
        remove(seq);
        this.seqList.remove(seq);
    }
}
