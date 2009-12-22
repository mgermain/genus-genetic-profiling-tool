package edu.udes.bio.genus.client.algo;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResolverServiceAsync {
    Request startAlgo(String id, AbsAlgorithm input, AsyncCallback<AbsAlgorithm> callback);

    Request getId(AsyncCallback<String> callback);

    Request stopAlgo(String id, AsyncCallback<Void> callback);
}
