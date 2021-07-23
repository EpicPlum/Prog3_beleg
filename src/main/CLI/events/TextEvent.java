package main.CLI.events;

import java.util.EventObject;

public abstract class TextEvent extends EventObject
{
    private String text;
    public TextEvent(Object source,String text) throws NullPointerException
    {
        super(source);
        if(text == null)
        {
            throw new NullPointerException("text ist null.");
        }
        this.text=text;
    }
    public String getText(){
        return this.text;
    }
}
