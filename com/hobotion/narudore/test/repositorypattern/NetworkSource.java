package com.hobotion.narudore.test.repositorypattern;

/**
 * Created by Ray on 7/15/2016.
 */
public class NetworkSource implements DataSource
{
    private static int counter = 0;

    @Override
    public Data getData()
    {
        Data data;
        try
        {
            // simulate network request
            Thread.sleep(2000);
            counter++;
            data = new Data("Data-" + counter);
        }
        catch (InterruptedException e)
        {
            data = null;
        }

        if (counter > 1 && counter < 3)
            return null;
        return data;
    }

    @Override
    public void cache (Data data)
    {
        // do nothing
    }
}
