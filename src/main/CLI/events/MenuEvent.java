package main.CLI.events;

import main.CLI.console;

public class MenuEvent extends InputEvent
{
    private console runner;

    public MenuEvent(Object source, String text, console runner)
    {
        super(source, text);
        if(runner == null)
        {
            throw new NullPointerException("console ist null.");
        }
        this.runner = runner;
    }

    public console getConsole()
    {
        return runner;
    }
}
