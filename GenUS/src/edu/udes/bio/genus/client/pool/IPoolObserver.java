package edu.udes.bio.genus.client.pool;

import edu.udes.bio.genus.client.pool.PoolObservable.NotifyMessage;

public interface IPoolObserver {

    public void addUpdate(PoolObservable o);

    public void delUpdate(PoolObservable o);

    public void modUpdate(PoolObservable o, NotifyMessage m);

}
