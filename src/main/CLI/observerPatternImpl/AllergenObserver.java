package main.CLI.observerPatternImpl;

/*
Observer
 */
public class AllergenObserver extends Observer_Impl
{
    private ObservableAllergen allergen;


    public AllergenObserver(ObservableAllergen allergen, String name)
    {
        super(name);
        this.allergen = allergen;
    }

}
