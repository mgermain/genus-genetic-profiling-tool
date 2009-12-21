package edu.udes.bio.genus.client.algo;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResolverServiceAsync {
    RequestBuilder resolveServer(String id, AbsAlgorithm input, AsyncCallback<AbsAlgorithm> callback);

    RequestBuilder getId(AsyncCallback<String> callback);
}
