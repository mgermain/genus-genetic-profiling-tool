package edu.udes.bio.genus.client.ui.canvas;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.objetdirect.tatami.client.Slider;

public class Scaler extends Composite {

    private double scales[];
    private final Slider sl;
    private final Drawer dr;

    public Scaler(Drawer dr) {
        this.dr = dr;
        this.sl = new Slider("vertical", 0, 20, 10, true);

        this.sl.addValueChangeHandler(new MyHandler());
        initscales();

        this.sl.setPixelSize(20, 200);

        ZoomUtil.addScaler(this);

        initWidget(this.sl);

        DOM.setStyleAttribute(getElement(), "border", "1px solid lightgray");
        DOM.setStyleAttribute(getElement(), "backgroundColor", "#e3e8f3");
    }

    private void initscales() {
        double scale;

        this.scales = new double[21];

        scale = 1;
        for (int i = 10; i < 21; i++) {
            this.scales[i] = scale;
            scale = scale / 0.8;
        }
        scale = 1;
        for (int i = 10; i >= 0; i--) {
            this.scales[i] = scale;
            scale = scale * 0.8;
        }
    }

    private class MyHandler implements ValueChangeHandler<Integer> {
        @Override
        public void onValueChange(ValueChangeEvent<Integer> event) {
            ZoomUtil.setZoom(Scaler.this.dr, Scaler.this.scales[event.getValue()]);
            System.out.println(event.getValue());
        }
    }

    public void trySetScale(double scale) {
        int index;

        index = -1;
        for (int i = 0; i < 21; i++) {
            // vive le double precision !
            if (this.scales[i] > (scale - 0.00000009) && this.scales[i] < (scale + 0.00000009)) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            this.sl.setValue(index);
        }
    }
}
