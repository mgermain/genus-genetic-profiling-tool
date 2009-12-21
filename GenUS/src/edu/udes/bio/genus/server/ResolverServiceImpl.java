package edu.udes.bio.genus.server;

import java.util.HashMap;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.ResolverService;

public class ResolverServiceImpl extends RemoteServiceServlet implements ResolverService {

    private static Object syncroot = new Object();
    private static HashMap<String, Thread> tasks = new HashMap<String, Thread>();
    private static long currId = 0;

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    public AbsAlgorithm resolveServer(String id, AbsAlgorithm algo) {

        synchronized (ResolverServiceImpl.syncroot) {

        }

        Thread.currentThread();
        algo.execute();
        return algo;
    }

    @Override
    public String getId() {
        long r;

        System.out.println("meeee");
        synchronized (ResolverServiceImpl.syncroot) {
            r = ResolverServiceImpl.currId;
            ResolverServiceImpl.currId = (ResolverServiceImpl.currId + 1L) % (Long.MAX_VALUE - 1L);
        }

        System.out.println("weeee");
        return String.valueOf(r);
    }

}
