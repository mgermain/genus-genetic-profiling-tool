package edu.udes.bio.genus.client.ui.menu;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.pool.IPoolObserver;
import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class Menu_Strands extends VerticalPanel implements IPoolObserver {

    private final HashMap<RNAssDrawable, Strand> strandHash = new HashMap<RNAssDrawable, Strand>();

    public Menu_Strands() {
        super();
        //
        // Setup base panel
        setWidth("195px");
        setBorderWidth(0);
        setSpacing(2);
        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

        // Create and setup button
        final Button btnAddStrands = new Button("Add RNA Strand", new AddRnaStrandButtonClick());
        btnAddStrands.setWidth("80%");
        add(btnAddStrands);

        // Make the menu observe the pool of strands
        GenUS.rnaPool.subscribe(this);
    }

    public void addStrandToMenu(RNAssDrawable o) {
        final Strand s = new Strand(o);
        this.strandHash.put(o, s);
        add(s);
    }

    public void delStrandFromMenu(RNAssDrawable o) {
        remove(this.strandHash.get(o));
        this.strandHash.remove(o);
    }

    class AddRnaStrandButtonClick implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            GenUS.propMenu.show();
        }
    }

    @Override
    public void addUpdate(PoolObservable o) {
        addStrandToMenu((RNAssDrawable) o);
    }

    @Override
    public void delUpdate(PoolObservable o) {
        delStrandFromMenu((RNAssDrawable) o);
    }

    @Override
    public void modUpdate(PoolObservable o, NotifyMessage m) {
        this.strandHash.get(o).updateName();
    }

}
