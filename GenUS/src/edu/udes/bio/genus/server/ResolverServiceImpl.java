/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Université de Sherbrooke
 * Contact: code.google.com/p/genus-genetic-profiling-tool/
 * 
 * This is a free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the GNU
 * Lesser General Public License for more details.
 *  
 * Constributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.server;

import java.util.HashMap;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.udes.bio.genus.client.algo.AbsAlgorithm;
import edu.udes.bio.genus.client.algo.ResolverService;

public class ResolverServiceImpl extends RemoteServiceServlet implements ResolverService {

    private static Object syncroot = new Object();
    private static HashMap<String, AbsAlgorithm> tasks = new HashMap<String, AbsAlgorithm>();
    private static long currId = 0;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Override
    public AbsAlgorithm startAlgo(String id, AbsAlgorithm algo) {

        synchronized (ResolverServiceImpl.syncroot) {
            if (ResolverServiceImpl.tasks.containsKey(id)) {
                ResolverServiceImpl.tasks.remove(id);
            }
            ResolverServiceImpl.tasks.put(id, algo);
        }

        algo.execute();

        synchronized (ResolverServiceImpl.syncroot) {
            if (ResolverServiceImpl.tasks.containsKey(id)) {
                ResolverServiceImpl.tasks.remove(id);
            }
        }
        return algo;
    }

    @Override
    public String getId() {
        long r;

        synchronized (ResolverServiceImpl.syncroot) {
            r = ResolverServiceImpl.currId;
            ResolverServiceImpl.currId = (ResolverServiceImpl.currId + 1L) % (Long.MAX_VALUE - 1L);
        }

        return String.valueOf(r);
    }

    @Override
    public void stopAlgo(String id) {
        synchronized (ResolverServiceImpl.syncroot) {
            try {
                if (ResolverServiceImpl.tasks.containsKey(id)) {
                    ResolverServiceImpl.tasks.get(id).setShouldStop(true);
                    ResolverServiceImpl.tasks.remove(id);
                }
            } catch (final Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
