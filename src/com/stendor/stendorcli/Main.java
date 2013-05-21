package com.stendor.stendorcli;
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
        btnSend.setOnClickListener(new SendListener());
        arduino = Arduino.factory(console, this);
    }

    public void execute(Command command)
    {
        if (command.getOperation().equals("pan") && arduino != null)
            arduino.pan(command.getParameter());
        else if (command.getOperation().equals("tilt") && arduino != null)
            arduino.tilt(command.getParameter());
        else if (command.getOperation().equals("kuyt") && arduino != null)
            arduino.linksVooruit(command.getParameter());
        else if (command.getOperation().equals("schaars") && arduino != null)
            arduino.linksAchteruit(command.getParameter());
        else if (command.getOperation().equals("robben") && arduino != null)
            arduino.rechtsVooruit(command.getParameter());
        else if (command.getOperation().equals("gregory") && arduino != null)
            arduino.rechtsAchteruit(command.getParameter());
        else
            console.println("Ongeldige opdracht");
    }

    private class SendListener implements android.view.View.OnClickListener
    {
        public void onClick(android.view.View arg0)
        {
            execute(new Command(input.getText().toString()));
            input.setText("", TextView.BufferType.EDITABLE);
        }
    }
}


