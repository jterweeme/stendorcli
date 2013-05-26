package com.stendor.stendorcli;
public class SendListener implements android.view.View.OnClickListener
{
    Main main;

    public SendListener(Main main)
    {
        this.main = main;
    }

    public void onClick(android.view.View arg0)
    {
        main.dispatch();
    }
}


