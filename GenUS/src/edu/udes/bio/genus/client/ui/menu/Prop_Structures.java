package edu.udes.bio.genus.client.ui.menu;

import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.GenUS;
import edu.udes.bio.genus.client.rna.RNAException;
import edu.udes.bio.genus.client.rna.RNAIncompleteException;
import edu.udes.bio.genus.client.rna.RNAss;

public class Prop_Structures extends PopupPanel {
    private String structure = "";
    private TextBox txtName = null;
    private TextBox txtStructure = null;

    private VerticalPanel panel = new VerticalPanel();
    protected boolean updateStruct;

    private void setTextBoxName() {
        this.txtName = new TextBox();
        this.txtName.setSize("400px", "20px");
    }

    private void setTextBoxSequence() {
        this.txtStructure = new TextBox();
        this.txtStructure.setSize("400px", "20px");

        // ADD FILTER TO THE Structure TEXTBOX
        final ChangeHandler dpChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                try {
                    RNAss.validateDotParenthisis(Prop_Structures.this.txtStructure.getText());
                    Prop_Structures.this.structure = Prop_Structures.this.txtStructure.getText();
                } catch (final RNAException e) {
                    Prop_Structures.this.txtStructure.setText(Prop_Structures.this.structure);
                }
            }
        };
        this.txtStructure.addChangeHandler(dpChangeHandler);

        final Set<Character> strucAllowedChars = new TreeSet<Character>();
        strucAllowedChars.add('.');
        strucAllowedChars.add('(');
        strucAllowedChars.add(')');

        final KeyUpHandler structUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (Prop_Structures.this.updateStruct || KeyCodes.KEY_DELETE == event.getNativeKeyCode() || KeyCodes.KEY_BACKSPACE == event.getNativeKeyCode()) {
                    Prop_Structures.this.updateStruct = false;
                    try {
                        RNAss.validateDotParenthisis(Prop_Structures.this.txtStructure.getText());
                        Prop_Structures.this.structure = Prop_Structures.this.txtStructure.getText();
                    } catch (final RNAIncompleteException e) {

                    } catch (final RNAException e) {
                        Prop_Structures.this.txtStructure.cancelKey();
                    }
                }
            }
        };
        this.txtStructure.addKeyUpHandler(structUpHandler);

        final KeyPressHandler structPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final Character c = event.getCharCode();

                if (strucAllowedChars.contains(c)) {
                    Prop_Structures.this.updateStruct = true;
                } else {
                    Prop_Structures.this.txtStructure.cancelKey();
                }
            }
        };
        this.txtStructure.addKeyPressHandler(structPressHandler);
    }

    public Prop_Structures() {
        center();
        setAnimationEnabled(true);
        setModal(true);

        add(this.panel);

        setTextBoxName();
        setTextBoxSequence();

        // #########################################
        // Block for name panel
        final HorizontalPanel namePanel = new HorizontalPanel();
        namePanel.setWidth("500px");
        this.panel.add(namePanel);

        final Label lblName = new Label("Name");
        lblName.setSize("100px", "20px");
        namePanel.add(lblName);
        namePanel.add(this.txtName);

        // #########################################
        // Block for sequence entry
        final HorizontalPanel nucPanel = new HorizontalPanel();
        nucPanel.setSize("500px", "20px");
        this.panel.add(nucPanel);

        final Label lblNuc = new Label("Structure");
        lblNuc.setTitle("DotPaenesis");
        lblNuc.setSize("100px", "20px");
        nucPanel.add(lblNuc);
        nucPanel.add(this.txtStructure);

        // #########################################
        // Add the action button panel
        final HorizontalPanel actionButtonPanel = new HorizontalPanel();
        actionButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        actionButtonPanel.setWidth("500px");

        // CANCEL BUTTON
        final Button btnCancel = new Button("Cancel");
        final ClickHandler cancelclick = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Prop_Structures.this.removeFromParent();
            }
        };
        btnCancel.addClickHandler(cancelclick);
        actionButtonPanel.add(btnCancel);

        // OK BUTTON
        final Button btnOk = new Button("OK");
        final ClickHandler okclick = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GenUS.mainMenu.structMenu.addNewStrucure(Prop_Structures.this.txtName.getText(), Prop_Structures.this.structure);
                Prop_Structures.this.removeFromParent();
            }
        };
        btnOk.addClickHandler(okclick);
        actionButtonPanel.add(btnOk);

        this.panel.add(actionButtonPanel);

    }
}
