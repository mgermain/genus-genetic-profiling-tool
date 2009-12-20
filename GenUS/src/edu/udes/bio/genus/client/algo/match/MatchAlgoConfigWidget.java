package edu.udes.bio.genus.client.algo.match;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import edu.udes.bio.genus.client.algo.AbsAlgoWidget;

public class MatchAlgoConfigWidget extends AbsAlgoWidget {
    private final static String DESCRIPTION = "This algorithm must be call with two parameters: 1-secondary structure, 2-primary sequence. It will return a secondary structure with the primary sequence set, where it is possible.";
    private final static String NAME = "Match Algorithm";

    private final MatchDialog dialog;
    public boolean started;
    public SimplePanel imageContainer;
    public MatchAlgoImageBundle imagesBundle;

    public interface MatchAlgoImageBundle extends ClientBundle {
        @Source("details.gif")
        public ImageResource detailsButtonIcon();

        @Source("redX.png")
        public ImageResource cancelButtonIcon();

        @Source("play.png")
        public ImageResource playButtonIcon();

        @Source("pause.png")
        public ImageResource pauseButtonIcon();

        @Source("check.png")
        public ImageResource checkButtonIcon();
    }

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
        hp.add(img);

        this.dialog = new MatchDialog(this, new OptionClickHandler());

        initWidget(hp);
    }

    private class CancelClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            removeFromParent();
        }
    }

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
        // TODO Auto-generated method stub
        return MatchAlgoConfigWidget.DESCRIPTION;
    }
}
