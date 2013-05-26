package com.stendor.stendorcli;
import android.hardware.usb.UsbManager;
import android.content.Context;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Arduino implements SerialInputOutputManager.Listener
{
    Console console;
    UsbSerialDriver driver;
    SerialInputOutputManager manager;
    Main main;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static Arduino factory(Console console, Context ctxt, Main main)
    {
        UsbManager manager = (UsbManager)ctxt.getSystemService(Context.USB_SERVICE);

        if (manager == null)
            return null;

        UsbSerialDriver driver = UsbSerialProber.acquire(manager);
        
        if (driver == null)
            return null;

        return new Arduino(console, driver, main);
    }

    private Arduino(Console console, UsbSerialDriver driver, Main main)
    {
        this.main = main;
        this.console = console;
        this.driver = driver;

        try
        {
            driver.open();
            driver.setBaudRate(9600);
        }
        catch (Exception e)
        {
            console.println("Kan baudrate niet instellen...");
        }

        console.println("Arduino geinitialiseerd");
        manager = new SerialInputOutputManager(driver, this);
        executor.submit(manager);
    }

    public void debug()
    {
        String command = String.format("d\r");

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Geen debug bericht...");
        }
    }

    public void onRunError(Exception e)
    {
    }

    public void onNewData(byte[] data)
    {
        main.runOnUiThread(new Dinges(data, main));
    }

    public void pan(int deg)
    {
        console.println("Pan: " + deg);
        String command = String.format("p %d\r", deg);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet pannen...");
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
            console.println("Kan niet tilten...");
        }
    }
    
    public void linksVooruit(int speed)
    {
        console.println("Links vooruit: " + speed);
        String command = String.format("q %d\r", speed);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet links vooruit...");
        }

    }

    public void linksAchteruit(int speed)
    {
        console.println("Links achteruit: " + speed);
        String command = String.format("a %d\r", speed);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet links achteruit...");
        }

    }
    public void rechtsVooruit(int speed)
    {
        console.println("Rechts vooruit: " + speed);
        String command = String.format("w %d\r", speed);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet rechts vooruit...");
        }

    }

    public void rechtsAchteruit(int speed)
    {
        console.println("Rechts achteruit: " + speed);
        String command = String.format("s %d\r", speed);

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Kan niet rechts achteruit...");
        }
    }

    public int trip()
    {
        String command = String.format("o\r");

        try
        {
            driver.write(command.getBytes(), 0);
        }
        catch (Exception e)
        {
            console.println("Geen trip informatie...");
        }

        return 0;
    }
}



