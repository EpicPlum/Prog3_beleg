package main.CLI.eventsImpl;

import main.CLI.events.InputEvent;

import java.util.EventListener;

public interface InputEventListener extends EventListener
{
    public void onInputEvent(InputEvent event);
}
