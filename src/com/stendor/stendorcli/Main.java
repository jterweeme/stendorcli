package com.stendor.stendorcli;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class Main extends android.app.Activity
{
    Console console;
    Button btnSend;
    EditText input;
    Arduino arduino;

    protected void onCreate(android.os.Bundle sis)
    {
        super.onCreate(sis);
        setContentView(R.layout.main);
        console = new Console((TextView)findViewById(R.id.txtConsole));
        btnSend = (Button)findViewById(R.id.btnSend);
        input = (EditText)findViewById(R.id.input);
        input.setOnEditorActionListener(new InputListener());
        btnSend.setOnClickListener(new SendListener(this));
        arduino = Arduino.factory(console, this, this);
    }

    public void execute(Command command)
    {
        if (arduino == null)
        {
            console.println("Geen Arduino! :S");
            return;
        }

        if (command.getOperation().equals("pan"))
            arduino.pan(command.getParameter());
        else if (command.getOperation().equals("tilt"))
            arduino.tilt(command.getParameter());
        else if (command.getOperation().equals("kuyt"))
            arduino.linksVooruit(command.getParameter());
        else if (command.getOperation().equals("schaars"))
            arduino.linksAchteruit(command.getParameter());
        else if (command.getOperation().equals("robben"))
            arduino.rechtsVooruit(command.getParameter());
        else if (command.getOperation().equals("gregory"))
            arduino.rechtsAchteruit(command.getParameter());
        else if (command.getOperation().equals("trip"))
            arduino.trip();
        else
            console.println("Ongeldige opdracht");
    }

    public void dispatch()
    {
        execute(new Command(input.getText().toString()));
        input.setText("", TextView.BufferType.EDITABLE);
    }

/*
    private class SendListener implements android.view.View.OnClickListener
    {
        public void onClick(android.view.View arg0)
        {
            dispatch();
        }
    }
*/

    private class InputLuisteraar implements EditText.OnKeyListener
    {
        public boolean onKey(android.view.View v, int keyCode, KeyEvent ev)
        {
            dispatch();
            return false;
        }
    }

    private class InputListener implements EditText.OnEditorActionListener
    {
        public boolean onEditorAction(TextView et, int actionId, KeyEvent ev)
        {
            dispatch();
            return false;
        }
    }

    public void ontvang(byte[] data)
    {
        console.println(data);
    }
}


