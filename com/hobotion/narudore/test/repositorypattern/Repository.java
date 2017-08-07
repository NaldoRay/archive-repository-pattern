package com.hobotion.narudore.test.repositorypattern;

import rx.Observable;

/**
 * Created by Ray on 7/15/2016.
 */
public abstract class Repository
{
    private static final int DEFAULT_STALE_TIMEOUT = 5_000; // data is stale (out of date) after 60s
    private final int staleTimeout;

    /**
     * Stale timeout is set to default of 1 minute (60s)
     */
    public Repository ()
    {
       this(DEFAULT_STALE_TIMEOUT);
    }

    /**
     * @param staleTimeout timeout before data is out-of-date
     */
    public Repository (int staleTimeout)
    {
        this.staleTimeout = staleTimeout;
    }

    protected final boolean isUpToDate (Data data)
    {
        return (data.getAge() < staleTimeout);
    }

    public abstract Observable<Data> getData();
}
