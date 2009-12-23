package edu.udes.bio.genus.client.ui.menu;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.udes.bio.genus.client.algo.AbsAlgoWidget;
import edu.udes.bio.genus.client.algo.AlgoWidgetMaker;
import edu.udes.bio.genus.client.algo.match.MatchAlgoWidgetMaker;

public class Menu_Algo extends Composite {

    private final VerticalPanel algosPanel;
    private ListBox lb;
    private final int widgetWidth;

    private final HashMap<String, AlgoWidgetMaker> algos;

    private static final String[] algoNames = { "Match Algorithm" };
    private static final AlgoWidgetMaker[] algoClasses = { new MatchAlgoWidgetMaker() };

    public Menu_Algo(int pxWidth) {//
        super();

        VerticalPanel vp;

        vp = new VerticalPanel();
        this.widgetWidth = pxWidth - 2;

        this.algosPanel = new VerticalPanel();

        vp.setWidth(pxWidth + "px");

        this.algosPanel.setWidth("100%");

        this.algos = new HashMap<String, AlgoWidgetMaker>();
        for (int i = 0; i < Menu_Algo.algoNames.length; i++) {
            this.algos.put(Menu_Algo.algoNames[i], Menu_Algo.algoClasses[i]);
        }

        vp.add(makeTopAlgos());

        vp.add(this.algosPanel);
        // vp.add(new MatchAlgoConfigWidget(pxWidth - 5));

        initWidget(vp);
    }

    private Widget makeTopAlgos() {
        VerticalPanel vp;
        HorizontalPanel hp;
        Button btn;

        vp = new VerticalPanel();

        hp = new HorizontalPanel();
        hp.add(new Label("Algo : "));
        this.lb = new ListBox();
        for (final String algoName : Menu_Algo.algoNames) {
            this.lb.addItem(algoName);
        }
        hp.add(this.lb);
        vp.add(hp);

        btn = new Button("Add algo");
        btn.addClickHandler(new AlgoAdderHandler());

        vp.add(btn);

        return vp;
    }

    private class AlgoAdderHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            AbsAlgoWidget w;

            w = Menu_Algo.this.algos.get(Menu_Algo.this.lb.getValue(Menu_Algo.this.lb.getSelectedIndex())).getWidget(Menu_Algo.this.widgetWidth);

            Menu_Algo.this.algosPanel.add(w);

            w.showOptions();
        }

    }

}