package main.CLI;

import main.CLI.events.*;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.GL.*;
import main.GL.interfaces.Allergen;
import main.IO.jbp;
import main.IO.jos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class console
{
    private static Scanner scnr = new Scanner(System.in);
    private static String menuEingabe;
    private Automat automat = new Automat(5);
    private ConsoleEventHandler consoleHandler;


    public String getMenuEingabe()
    {
        return menuEingabe;
    }

    public void setMenuEingabe(String menuEingabe) throws NullPointerException, InputMismatchException
    {
        if(menuEingabe == null)
        {
            throw new NullPointerException("Eingabe ist null.");
        }

        if(menuEingabe.length() < 0 || (!menuEingabe.equals(":c") && !menuEingabe.equals(":d") && !menuEingabe.equals(":r")
                && !menuEingabe.equals(":u") && !menuEingabe.equals(":p") && !menuEingabe.equals(":config") && !menuEingabe.equals(":q")))
        {
            throw new InputMismatchException("Ungueltig Eingabe.");
        }

        this.menuEingabe = menuEingabe;
    }

    public Automat getAutomat()
    {
        return this.automat;
    }

    public void setConsoleHandler(ConsoleEventHandler consoleHandler)
    {
        this.consoleHandler = consoleHandler;
    }


    public void addMode() throws InputMismatchException
    {
        System.out.println("-- EinfuegeModus --");
        System.out.println("[Herstellername] - fuegt einen Hersteller ein");
        System.out.println("[Kuchen-Typ] [Herstellername] [Preis] [Naehrwert] [Haltbarkeit] [kommaseparierte Allergene, einzelnes Komma fuer keine]" +
                            "[Obstsorte] [Kremsorte] - fuegt einen Kuchen ein\n");

        scnr.nextLine();

        String[] line = {};

        ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);

        //String[] line = scnr.nextLine().split(" ");
        if(this.consoleHandler != null)
            consoleHandler.handleArrayInput(input);

        line = input.getArray();

        String[] allergeneStrings;
        ArrayList<Allergen> allergene = new ArrayList<Allergen>();

        if(line == null)
        {
            throw new NullPointerException("Kann nicht null einfuegen");
        }
        //Herstellername
        if(line.length == 1)
        {
            automat.addHersteller(new Hersteller(line[0]));
        }
        else if(line.length >= 6)
        {


            if(!line[5].equals(","))
            {
                allergeneStrings = line[5].split(",");

                for (int i = 0; i < allergeneStrings.length; i++)
                {
                    for(Allergen loop : Allergen.values())
                    {
                        if((loop.toString()).equals(allergeneStrings[i]))
                        {
                            allergene.add(loop);
                        }
                    }
                }
            }

            if(line[0].equals("Kuchen"))
            {
                Kuchen k = new Kuchen();

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        k.setHersteller((Hersteller)h);
                    }
                }

                k.setPreis(new BigDecimal(Double.parseDouble(line[2])));
                k.setNaehrwert(Integer.parseInt(line[3]));
                k.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                k.setAllergene(allergene);
                k.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(k);
            }
            else if(line[0].equals("Kremkuchen"))
            {
                Kremkuchen kk = new Kremkuchen();

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        kk.setHersteller((Hersteller)h);
                    }
                }
                kk.setPreis(new BigDecimal(Double.parseDouble(line[2])));
                kk.setNaehrwert(Integer.parseInt(line[3]));
                kk.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                kk.setAllergene(allergene);
                kk.setKremsorte(line[6]);
                kk.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(kk);
            }
            else if(line[0].equals("Obstkuchen"))
            {
                Obstkuchen ok = new Obstkuchen();
                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        ok.setHersteller((Hersteller)h);
                    }
                }
                ok.setPreis(new BigDecimal(Double.parseDouble(line[2])));
                ok.setNaehrwert(Integer.parseInt(line[3]));
                ok.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ok.setAllergene(allergene);
                ok.setObstsorte(line[6]);
                ok.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(ok);
            }
            else if(line[0].equals("Obsttorte"))
            {
                Obsttorte ot = new Obsttorte();

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        ot.setHersteller((Hersteller)h);
                    }
                }
                ot.setPreis(new BigDecimal(Double.parseDouble(line[2])));
                ot.setNaehrwert(Integer.parseInt(line[3]));
                ot.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ot.setAllergene(allergene);
                ot.setObstsorte(line[6]);
                ot.setKremsorte(line[7]);
                ot.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(ot);
            }
            else
            {
                throw new InputMismatchException("Gibts kein Art dieser Kuchen.");
            }


        }
        else
            throw new InputMismatchException("Ungueltige Eingabe.");

    }

    public void deleteMode() throws InputMismatchException
    {
        System.out.println("-- LoeschModus --");
        System.out.println("[Hersteller] - Loescht den Produzenten");
        System.out.println("[Fachnummer] - Entfernt den Kuchen\n");

        scnr.nextLine();

        if(scnr.hasNextInt())
        {
            IntInputEvent intIn = new IntInputEvent(this,scnr.nextInt());
            if(this.consoleHandler != null)
                consoleHandler.handleIntInput(intIn);
            automat.removeKuchen(intIn.getNum());
            return;

        }
        else if(scnr.hasNext())
        {
            InputEvent stringIn = new InputEvent(this,scnr.next());
            if(this.consoleHandler != null)
                consoleHandler.handleInput(stringIn);
            automat.removeHersteller(stringIn.getText());
            return;
        }
            throw new InputMismatchException("Keine gueltige Hersteller oder Fachnummer.");
    }

    public void displayMode() throws InputMismatchException
    {

        System.out.println("-- AnzeigeModus --");
        System.out.println("hersteller - Anzeige der Hersteller mit der Anzahl der Kuchen");
        System.out.println("kuchen [Typ] - Anzeige der Kuchen gefiltert nach Typ");
        System.out.println("allergene i/e - Anzeige der vorhandenen / nicht vorhandenen Allergene\n");

        scnr.nextLine();

        //String[] line = scnr.nextLine().split(" ");
        String[] line = {};
        ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);

        if(this.consoleHandler != null)
            consoleHandler.handleArrayInput(input);

        for(int i = 0; i < line.length; i++)
        {
            if(line[0].equals("hersteller"))
            {
                System.out.println(automat.listHersteller());
                return;
            }
            else if(line[0].equals("kuchen"))
            {
                if(line[1].equals("Kuchen"))
                {
                    System.out.println(automat.listVerkaufsobjekte(2));
                    return;
                }
                else if(line[1].equals("Kremkuchen"))
                {
                    System.out.println(automat.listVerkaufsobjekte(3));
                    return;
                }
                else if(line[1].equals("Obstkuchen"))
                {
                    System.out.println(automat.listVerkaufsobjekte(4));
                    return;
                }
                else if(line[1].equals("Obsttorte"))
                {
                    System.out.println(automat.listVerkaufsobjekte(5));
                    return;
                }
                else
                {
                    throw new InputMismatchException("Falsche Eingabe.");
                }
            }
            else if(line[0].equals("allergene"))
            {
                if(line[1].equals("i"))
                {
                    System.out.println(automat.listAllergene(1));
                    return;
                }
                else if(line[1].equals("e"))
                {
                    System.out.println(automat.listAllergene(2));
                    return;
                }
                else
                    throw new InputMismatchException("Falsche Eingabe.");
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }
    }

    public void inspectionMode() throws InputMismatchException
    {
        System.out.println("-- AenderungsModus --");
        System.out.println("[Fachnummer] - setzt das Datum der Inspektion\n");
        int fachnummer = 0;
        Date date;
        int month = 0, day = 0, year = 0;

        if(scnr.hasNextInt())
        {

            //fachnummer = scnr.nextInt();
            IntInputEvent intIn = new IntInputEvent(this,scnr.nextInt());
            if(this.consoleHandler != null)
                consoleHandler.handleIntInput(intIn);
            fachnummer = intIn.getNum();

            System.out.println("[Jahr] [Monat] [Tag] - setzt das Datum");
            System.out.println("2001 6 31 - z.B.");
            if(scnr.hasNextInt())
            {
                scnr.nextLine();

                String[] line = {};
                ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);
                if(this.consoleHandler != null)
                    consoleHandler.handleArrayInput(input);

                if (line.length == 3)
                {
                    if (Integer.parseInt(line[0]) > 0 && Integer.parseInt(line[1]) > 0 && Integer.parseInt(line[2]) > 0)
                    {
                        year = Integer.parseInt(line[0]);
                        month = Integer.parseInt(line[1]);
                        day = Integer.parseInt(line[2]);

                        if (month > 12 || day > 31)
                        {
                            throw new InputMismatchException("Falsche Monat/Tag.");
                        }
                    } else
                        throw new InputMismatchException("Datum darf nicht negativ sein.");
                }
                else
                    throw new InputMismatchException("Falsche Eingabe." + Arrays.toString(line));
                date = new Date(year, month, day);
            }
            else
                throw new InputMismatchException("Braucht ein int.");
        }
        else
            throw new InputMismatchException("Braucht ein int.");

        System.out.println(automat.setInspektionsdatum(fachnummer, date));
    }

    public void persistenceMode() throws InputMismatchException
    {
        System.out.println("-- PersistenceModus --");
        System.out.println("Wie wuerden Sie die Automat speichern / laden?");
        System.out.println("saveJOS - speichert mittels JOS");
        System.out.println("loadJOS - laedt mittels JOS");
        System.out.println("saveJBP - speichert mittels JBP");
        System.out.println("loadJBP - laedt mittels JBP");

        String eingabe = "";
        scnr.nextLine();

        if(scnr.hasNext())
        {
            InputEvent stringIn = new InputEvent(this,scnr.next());
            if(this.consoleHandler != null)
                consoleHandler.handleInput(stringIn);
            eingabe = stringIn.getText();

            if(eingabe.equals("saveJOS"))
            {
                jos.saveAutomat("jos.ser", automat);
                System.out.println("Gespeichert speichert mittels JOS");
            }
            else if(eingabe.equals("loadJOS"))
            {
                automat = jos.loadAutomat("jos.ser");
                System.out.println("Geladen laedt mittels JOS");
            }
            else if(eingabe.equals("saveJBP"))
            {
                jbp.saveAutomat("jbp.xml", automat);
                System.out.println("Gespeichert mittels JBP");
            }
            else if (eingabe.equals("loadJBP"))
            {
                automat = jbp.loadAutomat("jbp.xml");
                System.out.println("Geladen mittels JBP");
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }
        else
            throw new InputMismatchException("Falsche Eingabe.");


    }

    public void start()
    {
        boolean loop = true;
        console runner = new console();

                System.out.println("\nKupec Kuchen Automat - Willem Kupec 577468\n");
                System.out.println("Wie viel Platz brauchen Sie?");

                if(scnr.hasNextInt())
                {
                    CapacityEvent cEvent = new CapacityEvent(this, scnr.nextInt(), automat);
                    if(this.consoleHandler != null)
                        consoleHandler.handleCapacity(cEvent);
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
                System.out.println(":config - Konfigurationsmodus");
                System.out.println(":q - Quit");


                MenuEvent mEvent = new MenuEvent(this, scnr.next(), runner);
                if(this.consoleHandler != null)
                    consoleHandler.handleMenu(mEvent);

                if (menuEingabe.equals(":c")) {
                    addMode();
                }

                if (menuEingabe.equals(":d")) {
                    deleteMode();
                }

                if (menuEingabe.equals(":r")) {
                    displayMode();
                }

                if (menuEingabe.equals(":u")) {
                    inspectionMode();
                }

                if (menuEingabe.equals(":p")) {
                    persistenceMode();
                }

                if (menuEingabe.equals(":q")) {
                    loop = false;
                }

            }
    }

/*
    public void menu()
    {
        boolean loop = true;
        console runner = new console();

        System.out.println("\nKupec Kuchen Automat - Willem Kupec 577468\n");
        System.out.println("Wie viel Platz brauchen Sie?");

        if(scnr.hasNextInt())
        {
            automat.setMaxSize(scnr.nextInt());
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
            System.out.println(":config - Konfigurationsmodus");
            System.out.println(":q - Quit");


            runner.setMenuEingabe(scnr.next());

            if (menuEingabe.equals(":c")) {
                addMode();
            }

            if (menuEingabe.equals(":d")) {
                deleteMode();
            }

            if (menuEingabe.equals(":r")) {
                displayMode();
            }

            if (menuEingabe.equals(":u")) {
                inspectionMode();
            }

            if (menuEingabe.equals(":p")) {
                persistenceMode();
            }

            if (menuEingabe.equals(":q")) {
                loop = false;
            }
        }
    }
 */
}
