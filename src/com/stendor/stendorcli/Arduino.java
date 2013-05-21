package com.stendor.stendorcli;
import android.hardware.usb.UsbManager;
import android.content.Context;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;

public class Arduino
{
    Console console;
    UsbSerialDriver driver;

    public static Arduino factory(Console console, Context ctxt)
    {
        UsbManager manager = (UsbManager)ctxt.getSystemService(Context.USB_SERVICE);

        if (manager == null)
            return null;

        UsbSerialDriver driver = UsbSerialProber.acquire(manager);
        
        if (driver == null)
            return null;

        return new Arduino(console, driver);
    }

    private Arduino(Console console, UsbSerialDriver driver)
    {
        this.console = console;
        this.driver = driver;

        try
        {
            driver.open();
            driver.setBaudRate(9600);
        }
        catch (Exception e)
        {
            console.println("Kan baudrate niet instellen");
        }

        console.println("Arduino geinitialiseerd");
    }

    public void pan(int deg)
    {
        console.println("Pan: " + deg);
        //String command = "p 100\r";
        String command = String.format("p %d\r", deg);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet pannen");
        }
    }

    public void tilt(int deg)
    {
        console.println("Tilt: " + deg);
        String command = String.format("t %d\r", deg);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet tilten");
        }
    }
    
    public void linksVooruit(int speed)
    {
    }

    public void linksAchteruit(int speed)
    {
    }
}



