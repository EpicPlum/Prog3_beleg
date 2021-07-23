package main.CLI.events;

import main.GL.Automat;

public class CapacityEvent extends IntInputEvent
{
    private Automat automat;

    public CapacityEvent(Object source, int num, Automat automat)
    {
        super(source, num);
        if(automat == null)
        {
            throw new NullPointerException("Automat ist null.");
        }
        this.automat = automat;
    }

    public Automat getAutomat()
    {
        return automat;
    }
}
