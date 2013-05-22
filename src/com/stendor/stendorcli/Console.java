package com.stendor.stendorcli;
import android.widget.TextView;

public class Console
{
    TextView tv;

    public Console(TextView tv)
    {
        this.tv = tv;
    }

    public void println(String s)
    {
        tv.append(s + "\n");
    }

    public void println(byte[] byteArray)
    {
        String s = "";

        for (byte b : byteArray)
            s += (char)b;

        tv.append(s);
    }
}


