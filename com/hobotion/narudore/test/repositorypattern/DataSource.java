package com.hobotion.narudore.test.repositorypattern;

/**
 * Created by Ray on 7/18/2016.
 */
public interface DataSource
{
    Data getData();
    void cache (Data data);
}
