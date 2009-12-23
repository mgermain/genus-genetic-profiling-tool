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
 * Contributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The Interface ResolverService.
 */
@RemoteServiceRelativePath("resolve")
public interface ResolverService extends RemoteService {
    AbsAlgorithm startAlgo(String id, AbsAlgorithm algo);

    String getId();

    void stopAlgo(String id);
}
