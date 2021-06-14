package main.CLI.events;

import java.util.EventObject;

public abstract class TextEvent extends EventObject
{
    private String text;
    public TextEvent(Object source,String text)
    {
        super(source);
        this.text=text;
    }
    public String getText(){
        return this.text;
    }
}
