package main.Simulation;

public class threadActivity
{
    private volatile static boolean adding = true;
    private volatile static boolean removing = false;

    public static boolean isAdding()
    {
        return adding;
    }

    public static boolean isRemoving()
    {
        return removing;
    }

    public static void startAdding()
    {
        adding = true;
    }

    public static void stopAdding()
    {
        adding = false;
    }

    public static void startRemoving()
    {
        removing = true;
    }

    public static void stopRemoving()
    {
        removing = false;
    }
}
