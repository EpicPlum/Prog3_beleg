package main.CLI.eventsImpl;

import main.CLI.events.OutputEvent;

import java.util.EventListener;

public interface OutputEventListener extends EventListener
{
    public void onOutputEvent(OutputEvent event);
}
