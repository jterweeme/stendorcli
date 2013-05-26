package com.stendor.stendorcli;
public class Dinges implements Runnable
{
    final byte[] data;
    Main mainActivity;

    public Dinges(final byte[] data, Main mainActivity)
    {
        this.data = data;
        this.mainActivity = mainActivity;
    }

    public void run()
    {
        mainActivity.ontvang(data);
    }
}



