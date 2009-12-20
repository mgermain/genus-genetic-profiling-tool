package edu.udes.bio.genus.client.algo.match;

import edu.udes.bio.genus.client.algo.AbsAlgoWidget;
import edu.udes.bio.genus.client.algo.AlgoWidgetMaker;

public class MatchAlgoWidgetMaker implements AlgoWidgetMaker {

    @Override
    public AbsAlgoWidget getWidget(int pxWidth) {
        // TODO Auto-generated method stub
        return new MatchAlgoConfigWidget(pxWidth);
    }

}
