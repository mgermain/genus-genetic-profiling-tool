package edu.udes.bio.genus.client.algo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("resolve")
public interface ResolverService extends RemoteService {
    AbsAlgorithm startAlgo(String id, AbsAlgorithm algo);

    String getId();

    void stopAlgo(String id);
}