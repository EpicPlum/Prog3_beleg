package main.CLI.observerPatternImpl;

/*
Observer
 */
public class CapacityObserver extends Observer_Impl
{
    private ObservableCapacity capacity;


    public CapacityObserver(ObservableCapacity capacity, String name)
    {
        super(name);
        this.capacity = capacity;
    }

}
