package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.objetdirect.tatami.client.gfx.Color;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.rna.RNAException;
import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class Prop_Strands extends AbsolutePanel {

    private TextBox txtName = null;
    private TextBox txtDp = null;
    private TextBox txtNuc = null;
    private ListBox lbColor = null;
    private ListBox lbStyle = null;
    private CustomButton btnHide = null;

    private VerticalPanel basePropertiesPanel = null;
    private RNAssDrawable rnass;

    public interface PropImageBundle extends ClientBundle {
        @Source("hide.png")
        public ImageResource hideButtonIcon();
    }

    private final PropImageBundle imagesBundle = GWT.create(PropImageBundle.class);

    private void init() {
        clear();
        this.basePropertiesPanel = new VerticalPanel();
        this.basePropertiesPanel.setBorderWidth(0);
        this.basePropertiesPanel.setSize("500px", "125px");
        this.add(this.basePropertiesPanel);

        this.txtName = new TextBox();
        this.txtDp = new TextBox();
        this.txtNuc = new TextBox();
        this.lbColor = new ListBox();
        this.lbStyle = new ListBox();

        // // Block for name panel
        final HorizontalPanel namePanel = new HorizontalPanel();
        namePanel.setWidth("500px");
        this.basePropertiesPanel.add(namePanel);

        final Label lblName = new Label("Name");
        lblName.setSize("100px", "20px");
        namePanel.add(lblName);

        this.txtName.setSize("400px", "20px");
        namePanel.add(this.txtName);

        final ChangeHandler nameChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setName(Prop_Strands.this.txtName.getText());
            }
        };
        this.txtName.addChangeHandler(nameChangeHandler);

        // // Block for dp entry
        final HorizontalPanel dpPanel = new HorizontalPanel();
        dpPanel.setWidth("500px");
        this.basePropertiesPanel.add(dpPanel);

        final Label lblDp = new Label("Structure");
        lblDp.setTitle("DotParentesis");
        lblDp.setSize("100px", "20px");
        dpPanel.add(lblDp);

        this.txtDp.setSize("400px", "20px");
        dpPanel.add(this.txtDp);

        final ChangeHandler dpChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                try {
                    Prop_Strands.this.rnass.setRNAssDotParentheses(Prop_Strands.this.txtDp.getText(), Prop_Strands.this.txtNuc.getText());
                } catch (final RNAException e) {
                    // TODO dire un erreur
                    Prop_Strands.this.txtDp.setText(Prop_Strands.this.rnass.getDotParentesis());
                }
            }
        };
        this.txtDp.addChangeHandler(dpChangeHandler);

        // // Block for nucleotide entry
        final HorizontalPanel nucPanel = new HorizontalPanel();
        nucPanel.setSize("500px", "20px");
        this.basePropertiesPanel.add(nucPanel);

        final Label lblNuc = new Label("Sequence");
        lblNuc.setSize("100px", "20px");
        nucPanel.add(lblNuc);

        this.txtNuc.setSize("400px", "20px");
        nucPanel.add(this.txtNuc);

        final ChangeHandler nucChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                try {
                    Prop_Strands.this.rnass.setRNAssGACU(Prop_Strands.this.txtNuc.getText());
                    Prop_Strands.this.txtNuc.setText(Prop_Strands.this.rnass.getSequence());
                } catch (final RNAException e) {
                    // TODO dire un erreur
                    e.printStackTrace();
                    Prop_Strands.this.txtNuc.setText(Prop_Strands.this.rnass.getSequence());
                }
            }
        };
        this.txtNuc.addChangeHandler(nucChangeHandler);

        // Dropdown for color type
        final HorizontalPanel oPanel = new HorizontalPanel();
        oPanel.setSize("500px", "20px");
        this.basePropertiesPanel.add(oPanel);

        final Label lblCol = new Label("Colors :");
        lblCol.setSize("50px", "20px");
        oPanel.add(lblCol);

        this.lbColor.setSize("200px", "20px");

        this.lbColor.addItem("JADE", "#00A86B");
        this.lbColor.addItem("JAM", "#A50B5E");
        this.lbColor.addItem("GREEN SEA", "#3CB371");
        this.lbColor.addItem("UBE", "#8878C3");
        this.lbColor.addItem("RED", Color.RED.toHex());
        this.lbColor.addItem("YELLOW", Color.YELLOW.toHex());
        this.lbColor.addItem("GREEN", Color.GREEN.toHex());
        this.lbColor.addItem("AQUA", Color.AQUA.toHex());
        this.lbColor.addItem("OLIVE", Color.OLIVE.toHex());
        this.lbColor.addItem("TEAL", Color.TEAL.toHex());
        this.lbColor.addItem("BLACK", Color.BLACK.toHex());
        this.lbColor.addItem("FUCHSIA", Color.FUCHSIA.toHex());
        oPanel.add(this.lbColor);

        final ChangeHandler colorChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setColor(Color.getColor(Prop_Strands.this.lbColor.getValue(Prop_Strands.this.lbColor.getSelectedIndex())));
            }
        };
        this.lbColor.addChangeHandler(colorChangeHandler);

        // Add the style list
        final Label lblStyle = new Label("Style :");
        lblStyle.setSize("50px", "20px");
        oPanel.add(lblStyle);

        this.lbStyle.setSize("200px", "20px");
        for (final RNAssDrawable.DrawStyle r : RNAssDrawable.DrawStyle.values()) {
            this.lbStyle.addItem(r.name().replace("_", " "), r.name());
        }
        oPanel.add(this.lbStyle);

        final ChangeHandler styleChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Strands.this.rnass.setDrawStyle(RNAssDrawable.DrawStyle.valueOf(Prop_Strands.this.lbStyle.getValue(Prop_Strands.this.lbStyle.getSelectedIndex())));
            }
        };
        this.lbStyle.addChangeHandler(styleChangeHandler);

        final HorizontalPanel okCancelPanel = new HorizontalPanel();
        okCancelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        okCancelPanel.setWidth("500px");

        // CANCEL BUTTON
        final Image iH = new Image(this.imagesBundle.hideButtonIcon());
        this.btnHide = new CustomButton(iH) {
            @Override
            protected void onClick() {
                getParent().getParent().setVisible(false);
            }
        };
        okCancelPanel.add(this.btnHide);

        this.basePropertiesPanel.add(okCancelPanel);

        DOM.setStyleAttribute(getElement(), "border", "1px solid grey");
        DOM.setStyleAttribute(getElement(), "background", "#e3e8f3");

        this.setVisible(true);
    }

    public void show() {
        init();

        try {
            this.rnass = new RNAssDrawable(GenUS.displayArea);
        } catch (final RNAException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        GenUS.rnaPool.addToPool(this.rnass);

        this.txtName.setFocus(true);

    };

    public void show(final RNAssDrawable po) {
        init();
        this.rnass = po;
        this.txtName.setText(po.getName());
        this.txtDp.setText(po.getDotParentesis());
        this.txtNuc.setText(po.getSequence());

        for (int i = 0; i < this.lbColor.getItemCount(); i++) {
            if (this.lbColor.getValue(i).equals(po.getColor().toHex())) {
                this.lbColor.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < this.lbStyle.getItemCount(); i++) {
            if (this.lbStyle.getValue(i).equals(po.getDrawStyle().name())) {
                this.lbStyle.setSelectedIndex(i);
                break;
            }
        }

        this.txtName.setFocus(true);
    }

}
