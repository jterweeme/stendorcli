package com.stendor.stendorcli;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

public class Luisteraar implements SerialInputOutputManager.Listener
{
    Main mainActivity;

    public Luisteraar(Main mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void onRunError(Exception e)
    {
    }

    public void onNewData(byte[] data)
    {
        mainActivity.runOnUiThread(new Dinges(data, mainActivity));
    }
}



