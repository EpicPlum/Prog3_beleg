package main.CLI.events;

import main.GL.Automat;

public class CapacityEvent extends IntInputEvent
{
    private Automat automat;

    public CapacityEvent(Object source, int num, Automat automat)
    {
        super(source, num);
        this.automat = automat;
    }

    public Automat getAutomat()
    {
        return automat;
    }
}
