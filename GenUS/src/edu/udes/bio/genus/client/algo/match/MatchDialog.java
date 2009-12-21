package edu.udes.bio.genus.client.algo.match;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class MatchDialog extends DialogBox {
    protected final static String EMPTYSTRING = "";

    private final ResolverServiceAsync resolverService = GWT.create(ResolverService.class);

    private final TextBox txtStrand;
    private final TextBox txtStructure;
    private final MatchAlgoConfigWidget parent;
    private MatchAlgorithm algo;
    private final OptionClickHandler och;
    private String currID;

    public MatchDialog(MatchAlgoConfigWidget parent, OptionClickHandler och) {
        Grid g;
        Button btn;

        this.och = och;
        this.parent = parent;

        g = new Grid(3, 2);
        g.setPixelSize(250, 250);

        // chaine, séquence, go
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
                    MatchDialog.this.resolverService.getId(new AsyncCallback<String>() {

                        @Override
                        public void onSuccess(String result) {
                            Image img;

                            try {
                                MatchDialog.this.currID = result;
                                MatchDialog.this.algo = new MatchAlgorithm(null, new RNAss[] { new RNAss(MatchDialog.this.txtStructure.getValue(), MatchDialog.this.txtStrand.getValue()) });
                                MatchDialog.this.resolverService.resolveServer(MatchDialog.this.currID, MatchDialog.this.algo, new MatchCallback(MatchDialog.this.parent));
                                MatchDialog.this.parent.started = true;
                                MatchDialog.this.parent.imageContainer.clear();
                                img = new Image(MatchDialog.this.parent.imagesBundle.pauseButtonIcon());
                                img.addClickHandler(new PauseClickHandler());
                                MatchDialog.this.parent.imageContainer.add(img);
                            } catch (final RNAException e) {
                                Window.alert(e.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub

                        }
                    });

                } else {
                    Window.alert("All fields must be set.");
                }
            }
        }
    }

    private class CancelClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            hide();
        }
    }

    private class PauseClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            // TODO
            Image img;

            MatchDialog.this.parent.imageContainer.clear();
            img = new Image(MatchDialog.this.parent.imagesBundle.playButtonIcon());// Image("Red_X.png");
            img.addClickHandler(MatchDialog.this.och);
            MatchDialog.this.parent.imageContainer.add(img);
        }
    }

}
