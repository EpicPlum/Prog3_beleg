package main.CLI.domainLogic;

import main.CLI.events.InputEvent;
import main.CLI.eventsImpl.InputEventHandler;

import java.util.Scanner;

public class ConsoleReader
{
    private InputEventHandler handler;

    public void setHandler(InputEventHandler handler)
    {
        this.handler = handler;
    }

    public void start()
    {
        try(Scanner scnr = new Scanner(System.in))
        {
            do
            {
                String menuEingabe = scnr.next();
                InputEvent e = new InputEvent(this, menuEingabe);

                if(this.handler != null)
                {
                    handler.handle(e);
                }
            } while(true);
        }
    }
}
