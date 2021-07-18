package main.CLI.events;

public class ArrayInputEvent extends InputEvent
{
    private String[] array;

    public ArrayInputEvent(Object source, String text, String[] array)
    {
        super(source, text);
        this.array = array;
    }

    public String[] getArray()
    {
        return array;
    }

    public void setArray(String[] array)
    {
        this.array = array;
    }
}
