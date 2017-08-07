package com.hobotion.narudore.test.repositorypattern;

/**
 * Created by Ray on 7/15/2016.
 */
public class DiskCache implements DataSource
{
    private Data data;

    @Override
    public Data getData()
    {
        Data ret = data;
        data = null;
        return ret;
    }

    @Override
    public void cache (Data data)
    {
        // TODO cache data to disk
        this.data = new Data("Disk-" + data.getValue(), data.getTimestamp());
    }
}
