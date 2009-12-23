package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.udes.bio.genus.client.rna.RNAss;

public class Prop_Sequences extends PopupPanel {
    private String sequence = "";
    private TextBox txtName = null;
    private TextBox txtSequence = null;

    private VerticalPanel panel = new VerticalPanel();

    private void setTextBoxName() {
        this.txtName = new TextBox();
        this.txtName.setSize("400px", "20px");
    }

    private void setTextBoxSequence() {
        this.txtSequence = new TextBox();
        this.txtSequence.setSize("400px", "20px");

        // ADD FILTER TO THE SEQUENCE TEXTBOX
        final ChangeHandler seqChangeHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.txtSequence.getText().toUpperCase());

                if (!RNAss.validateSequence(Prop_Sequences.this.txtSequence.getText())) {
                    Prop_Sequences.this.sequence = Prop_Sequences.this.txtSequence.getText();
                } else {
                    Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.sequence);
                }
            }
        };
        this.txtSequence.addChangeHandler(seqChangeHandler);

        final KeyUpHandler seqUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (Prop_Sequences.this.txtSequence.getText().matches("[gacuGACU ]*?")) {
                    Prop_Sequences.this.sequence = Prop_Sequences.this.txtSequence.getText().toUpperCase();
                }
                Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.sequence);

            }
        };
        this.txtSequence.addKeyUpHandler(seqUpHandler);

        final KeyPressHandler seqPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (Prop_Sequences.this.txtSequence.getText().matches("[gacuGACU ]*?")) {
                    Prop_Sequences.this.sequence = Prop_Sequences.this.txtSequence.getText().toUpperCase();
                    Prop_Sequences.this.txtSequence.setText(Prop_Sequences.this.sequence);
                }

            }
        };
        this.txtSequence.addKeyPressHandler(seqPressHandler);
    }

    public Prop_Sequences() {
        center();
        setModal(true);

        add(this.panel);

        setTextBoxName();
        setTextBoxSequence();

        this.panel.add(this.txtName);
        this.panel.add(this.txtSequence);
    }
}
