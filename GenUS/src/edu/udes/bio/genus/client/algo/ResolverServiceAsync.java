package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResolverServiceAsync {
    void resolveServer(AbsAlgorithm input, AsyncCallback<AbsAlgorithm> callback);
}
