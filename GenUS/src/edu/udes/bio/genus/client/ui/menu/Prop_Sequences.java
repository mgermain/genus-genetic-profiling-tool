package edu.udes.bio.genus.client.ui.menu;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Prop_Sequences extends PopupPanel {
    private String sequence = "";
    private TextBox txtName = null;
    private TextBox txtSequence = null;

    private void setTextBoxName() {
        this.txtName = new TextBox();
        this.txtName.setSize("400px", "20px");
    }

    private void setTextBoxSequence() {
        this.txtSequence = new TextBox();
        this.txtSequence.setSize("400px", "20px");

        // ADD FILTER TO THE SEQUENCE TEXTBOX
        // final ChangeHandler seqChangeHandler = new ChangeHandler() {
        // @Override
        // public void onChange(ChangeEvent event) {
        // try {
        // Prop_Strands.this.rnass.setRNAssGACU(Prop_Strands.this.txtSequence.getText());
        // } catch (final RNAException e) {
        // Prop_Strands.this.txtSequence.setText(Prop_Strands.this.rnass.getSequence());
        // }
        // }
        // };
        // this.txtSequence.addChangeHandler(seqChangeHandler);

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

        // setTextBoxName();
        setTextBoxSequence();

        // add(this.txtName);
        add(this.txtSequence);
    }
}
