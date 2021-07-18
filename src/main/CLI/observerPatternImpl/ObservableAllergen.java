package main.CLI.observerPatternImpl;

import main.GL.Automat;

/*
Subject
 */
public class ObservableAllergen extends Observable_Impl
{
    private int startNumAllergens;

    public ObservableAllergen(int startNumAllergens)
    {
        super();
        this.startNumAllergens = startNumAllergens;
    }

    public boolean checkAllergens(Automat automat)
    {
        //if allergens are changed, send message
        //checks allergens via numAllergene, total number of allergens in automat
        //go through available allergens in automat

        int before = automat.getNumAllergene();
        int after = automat.findNumAllergene();

        if(before == after)
        {
            System.out.println("Vorhandene Allergene waren nicht aendert seit letzes mal.");
            return false;
        }
        else
        {
            System.out.println("Vorhandene Allergene waren aendert seit letzes mal.");
            return true;
        }

    }
}
