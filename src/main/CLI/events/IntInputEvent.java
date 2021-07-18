package main.CLI.events;

import java.util.EventObject;

public class IntInputEvent extends EventObject
{
    private int num;
    public IntInputEvent(Object source, int num)
    {
        super(source);
        this.num = num;
    }
    public int getNum()
    {
        return this.num;
    }
}
