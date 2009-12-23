package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class Strand extends HorizontalPanel {//

    private RNAssDrawable poolObj = null;
    private final SimpleCheckBox cbxDisplay = new SimpleCheckBox();
    private final Label sName = new Label();

    public interface StrandImageBundle extends ClientBundle {
        @Source("details.png")
        public ImageResource detailsButtonIcon();

        @Source("red_x.png")
        public ImageResource cancelButtonIcon();
    }

    StrandImageBundle imagesBundle = GWT.create(StrandImageBundle.class);

    public Strand(RNAssDrawable o) {
        super();
        this.poolObj = o;

        // Setup main panel
        DOM.setStyleAttribute(getElement(), "border", "1px solid #e3e8f3");
        setSize("90%", "20px");
        setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        // Create and setup checkbox
        this.cbxDisplay.setChecked(true);
        this.cbxDisplay.setPixelSize(16, 16);
        final ClickHandler cbxClickHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Strand.this.poolObj.setVisible(Strand.this.cbxDisplay.isChecked());
            }
        };
        this.cbxDisplay.addClickHandler(cbxClickHandler);
        add(this.cbxDisplay);

        // Create and setup label for the name of the strand
        this.sName.setPixelSize(132, 20);
        updateName();
        add(this.sName);

        // Create and setup Edit button
        final Image iE = new Image(this.imagesBundle.detailsButtonIcon());
        final CustomButton btnEdit = new CustomButton(iE) {
            @Override
            protected void onClick() {
                GenUS.propMenu.show(Strand.this.poolObj);
            }
        };
        btnEdit.setPixelSize(16, 16);
        btnEdit.setTitle("Details");
        add(btnEdit);

        // TODO Include the image in the project
        final Image iD = new Image(this.imagesBundle.cancelButtonIcon());
        final CustomButton btnDel = new CustomButton(iD) {
            @Override
            protected void onClick() {
                if (DOM.eventGetCtrlKey(DOM.eventGetCurrentEvent()) || Window.confirm("Are you sure you want to delete this strand ?")) {
                    GenUS.rnaPool.removeFromPool(Strand.this.poolObj);
                }
            }
        };
        btnDel.setSize("16px", "16px");
        btnDel.setTitle("Press Ctrl to delete without confirmation.");
        add(btnDel);

        // Setup Widget
        final MouseOverHandler overHandler = new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                DOM.setStyleAttribute(getElement(), "backgroundColor", "#e3e8f3");
                DOM.setStyleAttribute(getElement(), "border", "1px solid lightgrey");
            }
        };
        addDomHandler(overHandler, MouseOverEvent.getType());
        final MouseOutHandler leaveHandler = new MouseOutHandler() {

            @Override
            public void onMouseOut(MouseOutEvent event) {
                DOM.setStyleAttribute(getElement(), "backgroundColor", "white");
                DOM.setStyleAttribute(getElement(), "border", "1px solid #e3e8f3");
            }
        };
        addDomHandler(leaveHandler, MouseOutEvent.getType());
    }

    public String getName() {
        return this.sName.getText();
    }

    public void updateName() {
        final int maxWidth = 14;
        this.sName.setTitle(this.poolObj.getName());
        if (this.poolObj.getName().length() > maxWidth) {
            this.sName.setText(this.poolObj.getName().substring(0, maxWidth - 1) + "...");
        } else {
            this.sName.setText(this.poolObj.getName());
        }
    }
}
