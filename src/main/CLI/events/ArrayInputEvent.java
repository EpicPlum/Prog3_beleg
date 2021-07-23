package main.CLI.events;

public class ArrayInputEvent extends InputEvent
{
    private String[] array;

    public ArrayInputEvent(Object source, String text, String[] array)
    {
        super(source, text);
        setArray(array);
    }

    public String[] getArray()
    {
        return array;
    }

    public void setArray(String[] array)
    {
        if(array == null)
        {
            throw new NullPointerException("Array darf nicht null sein.");
        }
        this.array = array;
    }

}
