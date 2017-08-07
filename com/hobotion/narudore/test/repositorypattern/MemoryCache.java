package com.hobotion.narudore.test.repositorypattern;


/**
 * Created by Ray on 7/15/2016.
 */
public class MemoryCache implements DataSource
{
    private Data data;
    private int c;

    @Override
    public Data getData()
    {
        c--;
        if (c == 0)
            data = null;

        return data;
    }

    @Override
    public void cache (Data data)
    {
        c = 3;
        this.data = new Data("memory-" + data.getValue(), data.getTimestamp());
    }
}
