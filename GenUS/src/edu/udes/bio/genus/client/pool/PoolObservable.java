package edu.udes.bio.genus.client.pool;

import java.util.ArrayList;

public class PoolObservable {

    public enum NotifyMessage {
        ADD, DEL, UPDATE
    };

    private final ArrayList<IPoolObserver> observers = new ArrayList<IPoolObserver>();

    public void addObserver(IPoolObserver o) {
        this.observers.add(o);
    }

    public void removeObserver(IPoolObserver o) {
        this.observers.remove(o);
    }

    public void notifyObserversAdd() {
        for (final IPoolObserver o : this.observers) {
            o.addUpdate(this);
        }
    }

    public void notifyObserversDel() {
        for (final IPoolObserver o : this.observers) {
            o.delUpdate(this);
        }
    }

    public void notifyObserversMod(NotifyMessage m) {
        for (final IPoolObserver o : this.observers) {
            o.modUpdate(this, m);
        }
    }
}
