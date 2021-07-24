package main.CLI;

import main.CLI.events.*;
import main.GL.Automat;

import java.util.InputMismatchException;

public class altConsole extends console
{
    public altConsole()
    {
        super();
    }

    public altConsole(Automat automat)
    {
        super(automat);
    }

    @Override
    public void deleteMode() throws InputMismatchException
    {
        System.out.println("-- LoeschModus --");
        System.out.println("[Fachnummer] - Entfernt den Kuchen\n");

        getScanner().nextLine();

        if(getScanner().hasNextInt())
        {
            IntInputEvent intIn = new IntInputEvent(this,getScanner().nextInt());
            if(this.getConsoleHandler() != null)
                getAutomat().removeKuchen(getConsoleHandler().handleIntInput(intIn));
            return;

        }
        else
            throw new InputMismatchException("Keine gueltige Hersteller oder Fachnummer.");
    }

    @Override
    public void displayMode() throws InputMismatchException
    {
        System.out.println("-- AnzeigeModus --");
        System.out.println("hersteller - Anzeige der Hersteller mit der Anzahl der Kuchen");
        System.out.println("kuchen [Typ] - Anzeige der Kuchen gefiltert nach Typ\n");

        getScanner().nextLine();

        String[] line = {};
        ArrayInputEvent input = new ArrayInputEvent(this, getScanner().nextLine(), line);

        if(this.getConsoleHandler() != null)
        {
            getConsoleHandler().handleArrayInput(input);
        }

        line = input.getArray();

        for(int i = 0; i < line.length; i++)
        {
            if(line[0].equals("hersteller"))
            {
                System.out.println(getAutomat().listHersteller());
                return;
            }
            else if(line[0].equals("kuchen"))
            {
                if(line[1].equals("Kuchen"))
                {
                    System.out.println(getAutomat().listVerkaufsobjekte(2));
                    return;
                }
                else if(line[1].equals("Kremkuchen"))
                {
                    System.out.println(getAutomat().listVerkaufsobjekte(3));
                    return;
                }
                else if(line[1].equals("Obstkuchen"))
                {
                    System.out.println(getAutomat().listVerkaufsobjekte(4));
                    return;
                }
                else if(line[1].equals("Obsttorte"))
                {
                    System.out.println(getAutomat().listVerkaufsobjekte(5));
                    return;
                }
                else
                {
                    throw new InputMismatchException("Falsche Eingabe.");
                }
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }
    }

    @Override
    public void start()
    {
        boolean loop = true;
        console runner = new console();

        System.out.println("\nKupec Kuchen Automat - Willem Kupec 577468\n");
        System.out.println("Wie viel Platz brauchen Sie?");

        if(getScanner().hasNextInt())
        {
            CapacityEvent cEvent = new CapacityEvent(this, getScanner().nextInt(), getAutomat());
            if(this.getConsoleHandler() != null)
                getConsoleHandler().handleCapacity(cEvent);
        }
        else
            throw new InputMismatchException("Braucht einen Integer.");

        while(loop)
        {
            System.out.println("Welche Modus moechten Sie?");
            System.out.println(":c - Einfuegemodus");
            System.out.println(":d - Loeschmodus");
            System.out.println(":r - Anzeigemodus");
            System.out.println(":u - Aenderungsmodus");
            System.out.println(":p - Persistenzmodus");
            System.out.println(":q - Quit");


            MenuEvent mEvent = new MenuEvent(this, getScanner().next(), runner);
            if(this.getConsoleHandler() != null)
                getConsoleHandler().handleMenu(mEvent);

            if (getMenuEingabe().equals(":c")) {
                addMode();
            }

            if (getMenuEingabe().equals(":d")) {
                deleteMode();
            }

            if (getMenuEingabe().equals(":r")) {
                displayMode();
            }

            if (getMenuEingabe().equals(":u")) {
                inspectionMode();
            }

            if (getMenuEingabe().equals(":p")) {
                persistenceMode();
            }

            if (getMenuEingabe().equals(":q")) {
                loop = false;
            }

            notifyObserversCapacity();
        }
    }
}
