package main.CLI.eventsImpl;

import main.CLI.events.*;

import java.util.EventListener;

public interface ConsoleEventListener extends EventListener
{
    public void onCapacityEvent(CapacityEvent event);
    public void onIntInputEvent(IntInputEvent event);
    public void onInputEvent(InputEvent event);
    public void onMenuEvent(MenuEvent event);
    public void onArrayInputEvent(ArrayInputEvent event);
}
