package main.CLI.observerPatternImpl;

/*
Observer
 */
public class AllergenObserver extends Observer_Impl
{
    public AllergenObserver(main.CLI.console console, String name)
    {
        super(console, name);
        console.addObserver(this);
    }

    @Override
    public boolean update()
    {
        int before = getConsole().getAutomat().getNumAllergene();

        int after = getConsole().getAutomat().findNumAllergene();

        if(before == after)
        {
            //System.out.println("Vorhandene Allergene waren nicht aendert seit letzes mal.");
            return false;
        }
        else
        {
            System.out.println("Vorhandene Allergene waren aendert seit letzes mal.");
            return true;
        }
    }

}
