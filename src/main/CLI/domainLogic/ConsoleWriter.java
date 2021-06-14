package main.CLI.domainLogic;

import main.CLI.events.OutputEvent;
import main.CLI.eventsImpl.OutputEventHandler;

public class ConsoleWriter
{
    private OutputEventHandler handler;

    public void setHandler(OutputEventHandler handler)
    {
        this.handler = handler;
    }

    public void start()
    {
            do
            {
                String menuAusgabe =
                "Welche Modus moechten Sie?" + "\n" +
                ":c - Einfuegemodus" + "\n" +
                ":d - Loeschmodus" + "\n" +
                ":r - Anzeigemodus" + "\n" +
                ":u - Aenderungsmodus" + "\n" +
                ":p - Persistenzmodus" + "\n" +
                ":config - Konfigurationsmodus" + "\n" +
                ":q - Quit" + "\n";

                OutputEvent e = new OutputEvent(this, menuAusgabe);

                if(this.handler != null)
                {
                    handler.handle(e);
                }
            } while(true);
    }
}
