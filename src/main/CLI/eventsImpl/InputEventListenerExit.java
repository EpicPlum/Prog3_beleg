package main.CLI.eventsImpl;

import main.CLI.events.InputEvent;

public class InputEventListenerExit implements InputEventListener
{
    private String name;

    public InputEventListenerExit(String name)
    {
        this.name = name;
    }
    @Override
    public void onInputEvent(InputEvent event)
    {
        if(event.getText() != null && event.getText().equals(":q"))
        {
            System.exit(0);
        }
    }
}
