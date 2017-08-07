package com.hobotion.narudore.test.repositorypattern;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Ray on 7/15/2016.
 */
public class DefaultMultipleRepository extends Repository
{
    private DataSource memory;
    private DataSource disk;
    private DataSource network;

    public DefaultMultipleRepository()
    {
        memory = new MemoryCache();
        disk = new DiskCache();
        network = new NetworkSource();
    }

    public Observable<Data> getData()
    {
        // accept only data that's up-to-date
        return Observable
            .concat(
                getObservableMemory(),
                getObservableDisk(),
                getObservableNetwork()
            )
            .takeFirst(new Func1<Data, Boolean>()
            {
                @Override
                public Boolean call (Data data)
                {
                    return data != null && isUpToDate(data);
                }
            });
            //.switchIfEmpty(getObservableDisk());
    }

    private Observable<Data> getObservableMemory()
    {
       return getObservable(memory);
    }

    private Observable<Data> getObservableDisk()
    {
        Observable<Data> observable = getObservable(disk).doOnNext(new Action1<Data>()
        {
            @Override
            public void call(Data data)
            {
                if (data != null)
                    memory.cache(data);
            }
        });

        return observable;
    }

    private Observable<Data> getObservableNetwork()
    {
        Observable<Data> observable = getObservable(network).doOnNext(new Action1<Data>()
        {
            @Override
            public void call(Data data)
            {
                if (data != null)
                {
                    disk.cache(data);
                    memory.cache(data);
                }
            }
        });

        return observable;
    }

    private Observable<Data> getObservable (final DataSource dataSource)
    {
        Observable<Data> observable = Observable.create(new Observable.OnSubscribe<Data>()
        {
            @Override
            public void call(Subscriber<? super Data> subscriber)
            {
                subscriber.onNext(dataSource.getData());
                subscriber.onCompleted();
            }
        });

        return observable;
    }
}
