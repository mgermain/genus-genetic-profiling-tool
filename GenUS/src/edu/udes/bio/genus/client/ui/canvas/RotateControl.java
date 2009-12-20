package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;

public class RotateControl extends Composite {
    public RotateControl(Drawer dr) {
        super();

        AbsolutePanel ap;

        ClickHandler ch;
        Button btn;

        ap = new AbsolutePanel();
        ap.setPixelSize(80, 40);

        ch = new RotateLeftHandler();
        btn = new Button();
        btn.addClickHandler(ch);
        btn.setText("<--");
        ap.add(btn, 0, 0);

        ch = new RotateRightHandler();
        btn = new Button();
        btn.addClickHandler(ch);
        btn.setText("-->");
        ap.add(btn, 40, 0);

    }

    private class RotateLeftHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub

        }

    }

    private class RotateRightHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub

        }

    }
}
