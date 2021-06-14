package main.CLI.observerPatternImpl;

/*
Observer
 */
public class CapacityObserver extends CounterObserver
{
    private ObservableCapacity capacity;


    public CapacityObserver(ObservableCapacity capacity, String name)
    {
        super(name);
        this.capacity = capacity;
    }

}
