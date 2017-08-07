package com.hobotion.narudore.test.repositorypattern;

/**
 * Created by Ray on 7/18/2016.
 */
public class Data
{
    private final String value;
    private final long timestamp;

    public Data (String value)
    {
        this(value, System.currentTimeMillis());
    }

    public Data (String value, long timestamp)
    {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue()
    {
        return this.value;
    }

    public long getTimestamp ()
    {
        return timestamp;
    }

    public long getAge()
    {
        return System.currentTimeMillis() - timestamp;
    }
}
