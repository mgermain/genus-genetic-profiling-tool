package edu.udes.bio.genus.client.pool;

import java.util.ArrayList;

import edu.udes.bio.genus.client.rna.RNAssDrawable;

public class Pool {

    private final ArrayList<RNAssDrawable> poolObjectList = new ArrayList<RNAssDrawable>();
    private final ArrayList<IPoolObserver> observers = new ArrayList<IPoolObserver>();

    public void addToPool(RNAssDrawable po) {
        // Add new object to the pool
        this.poolObjectList.add(po);

        // Notify everyone
        for (final IPoolObserver observer : this.observers) {
            po.addObserver(observer);
        }

        po.notifyObserversAdd();
    }

    public void removeFromPool(RNAssDrawable po) {
        // Remove from the pool
        this.poolObjectList.remove(po);

        // Notify everyone
        po.notifyObserversDel();
    }

    public void subscribe(IPoolObserver o) {
        this.observers.add(o);

        for (final RNAssDrawable p : this.poolObjectList) {
            p.addObserver(o);
        }
    }

    public void unsubscribe(IPoolObserver o) {
        for (final RNAssDrawable p : this.poolObjectList) {
            p.removeObserver(o);
        }

        this.observers.remove(o);
    }

    public ArrayList<RNAssDrawable> getAll() {
        ArrayList<RNAssDrawable> res;

        res = new ArrayList<RNAssDrawable>();

        res.addAll(this.poolObjectList);

        return res;

    }

}
