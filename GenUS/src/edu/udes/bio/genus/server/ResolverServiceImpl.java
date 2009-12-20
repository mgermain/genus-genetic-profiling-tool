package edu.udes.bio.genus.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.ResolverService;

public class ResolverServiceImpl extends RemoteServiceServlet implements ResolverService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public AbsAlgorithm resolveServer(AbsAlgorithm algo) {
        // TODO Auto-generated method stub
        algo.execute();
        return algo;
    }

}
