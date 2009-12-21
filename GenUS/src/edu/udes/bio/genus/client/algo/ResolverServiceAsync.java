package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResolverServiceAsync {
    void startAlgo(String id, AbsAlgorithm input, AsyncCallback<AbsAlgorithm> callback);

    void getId(AsyncCallback<String> callback);

    void stopAlgo(String id, AsyncCallback<Void> callback);
}
