package com.stendor.stendorcli;
import java.util.Scanner;

public class Command
{
    String operation;
    int parameter;

    public Command(String command)
    {
        Scanner tokenizer = new Scanner(command);
        String parameter = null;

        if (tokenizer.hasNext())
            operation = tokenizer.next();

        if (tokenizer.hasNext())
            parameter = tokenizer.next();

        if (parameter != null)
            this.parameter = Integer.parseInt(parameter);
    }

    public String getOperation()
    {
        return operation;
    }

    public int getParameter()
    {
        return parameter;
    }
}


