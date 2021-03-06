package main.CLI;

import main.CLI.events.*;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.CLI.observerPattern.ObservableAllergen;
import main.CLI.observerPattern.ObservableCapacity;
import main.CLI.observerPattern.Observer;
import main.CLI.observerPatternImpl.AllergenObserver;
import main.CLI.observerPatternImpl.CapacityObserver;
import main.CLI.observerPatternImpl.Observable_Impl;
import main.GL.*;
import main.GL.dekorator.Belag;
import main.GL.interfaces.*;
import main.IO.jbp;
import main.IO.jos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class console extends Observable_Impl implements ObservableCapacity, ObservableAllergen {
    private static Scanner scnr = new Scanner(System.in);
    private static String menuEingabe;
    private Automat automat;
    private ConsoleEventHandler consoleHandler;

    public console() {
        automat = new Automat(5);
        consoleHandler = new ConsoleEventHandler();
    }

    public console(Automat automat) {
        this.automat = automat;
        consoleHandler = new ConsoleEventHandler();
    }

    public static Scanner getScanner() {
        return scnr;
    }

    public String getMenuEingabe() {
        return menuEingabe;
    }

    public void setMenuEingabe(String menuEingabe) throws NullPointerException, InputMismatchException {
        if (menuEingabe == null) {
            throw new NullPointerException("Eingabe ist null.");
        }

        if (menuEingabe.length() < 0 || (!menuEingabe.equals(":c") && !menuEingabe.equals(":d") && !menuEingabe.equals(":r")
                && !menuEingabe.equals(":u") && !menuEingabe.equals(":p") && !menuEingabe.equals(":config") && !menuEingabe.equals(":q"))) {
            throw new InputMismatchException("Ungueltig Eingabe.");
        }

        this.menuEingabe = menuEingabe;
    }

    public Automat getAutomat() {
        return this.automat;
    }

    public ConsoleEventHandler getConsoleHandler() {
        return consoleHandler;
    }

    public void setConsoleHandler(ConsoleEventHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
    }


    public boolean addMode() throws InputMismatchException {
        System.out.println("-- EinfuegeModus --");
        System.out.println("[Herstellername] - fuegt einen Hersteller ein");
        System.out.println("[Kuchen-Typ] [Herstellername] [Preis] [Naehrwert] [Haltbarkeit] [kommaseparierte Allergene, einzelnes Komma fuer keine]" +
                "[Obstsorte] [Kremsorte] - fuegt einen Kuchen ein\n");

        scnr.nextLine();

        String[] line = {};

        ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);

        if (this.consoleHandler != null)
            consoleHandler.handleArrayInput(input);

        line = input.getArray();

        if (line == null) {
            throw new NullPointerException("Kann nicht null einfuegen");
        }
        //Herstellername
        if (line.length == 1) {
            automat.addHersteller(new Hersteller(line[0]));
            return true;
        } else if (line.length >= 6) {
            int kLength = 5;


            if (line[0].equals("Kuchen")) {
                Kuchen k = new Kuchen();
                Kuchenbar kFinal;
                Kuchenbar kTemp;

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        k.setHersteller((Hersteller) h);
                    }
                }

                k.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                k.setNaehrwert(Integer.parseInt(line[3]));
                k.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                k.setAllergene(determineAllergene(line[5]));

                kTemp = k;

                if (line.length >= 7) {

                    for (int i = 1; i < line.length / 5; i++) {
                        BigDecimal bPreis = new BigDecimal(Double.parseDouble(commaPreisInput(line[i * kLength + 1])));
                        int bNaehrwert = Integer.parseInt(line[i * kLength + 2]);
                        Duration bHaltbarkeit = Duration.ofDays(Long.parseLong(line[i * kLength + 3]));
                        HashSet<Allergen> bAllergene = determineAllergene(line[i * kLength + 4]);
                        String bName = line[i * kLength + 5];


                        kTemp = new Belag(bName, bPreis, bNaehrwert, bHaltbarkeit, bAllergene, k, kTemp);
                    }
                }
                kFinal = kTemp;

                automat.add(kFinal);
                return true;
            } else if (line[0].equals("Kremkuchen")) {
                Kremkuchen kk = new Kremkuchen();
                Kremkuchenbar kFinal;
                Kremkuchenbar kTemp;


                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        kk.setHersteller((Hersteller) h);
                    }
                }
                kk.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                kk.setNaehrwert(Integer.parseInt(line[3]));
                kk.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                kk.setAllergene(determineAllergene(line[5]));
                kk.setKremsorte(line[6]);

                kTemp = kk;

                if (line.length >= 8) {

                    for (int i = 1; i < line.length / 5; i++) {
                        BigDecimal bPreis = new BigDecimal(Double.parseDouble(commaPreisInput(line[i * kLength + 2])));
                        int bNaehrwert = Integer.parseInt(line[i * kLength + 3]);
                        Duration bHaltbarkeit = Duration.ofDays(Long.parseLong(line[i * kLength + 4]));
                        HashSet<Allergen> bAllergene = determineAllergene(line[i * kLength + 5]);
                        String bName = line[i * kLength + 6];


                        kTemp = new Belag(bName, bPreis, bNaehrwert, bHaltbarkeit, bAllergene, kk, kTemp);
                    }
                }
                kFinal = kTemp;

                automat.add(kFinal);
                return true;
            } else if (line[0].equals("Obstkuchen")) {
                Obstkuchen ok = new Obstkuchen();
                Obstkuchenbar kFinal;
                Obstkuchenbar kTemp;

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        ok.setHersteller((Hersteller) h);
                    }
                }
                ok.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                ok.setNaehrwert(Integer.parseInt(line[3]));
                ok.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ok.setAllergene(determineAllergene(line[5]));
                ok.setObstsorte(line[6]);

                kTemp = ok;

                if (line.length >= 8) {

                    for (int i = 1; i < line.length / 5; i++) {
                        BigDecimal bPreis = new BigDecimal(Double.parseDouble(commaPreisInput(line[i * kLength + 2])));
                        int bNaehrwert = Integer.parseInt(line[i * kLength + 3]);
                        Duration bHaltbarkeit = Duration.ofDays(Long.parseLong(line[i * kLength + 4]));
                        HashSet<Allergen> bAllergene = determineAllergene(line[i * kLength + 5]);
                        String bName = line[i * kLength + 6];


                        kTemp = new Belag(bName, bPreis, bNaehrwert, bHaltbarkeit, bAllergene, ok, kTemp);
                    }
                }
                kFinal = kTemp;

                automat.add(kFinal);
                return true;
            } else if (line[0].equals("Obsttorte")) {
                Obsttorte ot = new Obsttorte();
                Obsttortebar kFinal;
                Obsttortebar kTemp;

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        ot.setHersteller((Hersteller) h);
                    }
                }
                ot.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                ot.setNaehrwert(Integer.parseInt(line[3]));
                ot.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ot.setAllergene(determineAllergene(line[5]));
                ot.setObstsorte(line[6]);
                ot.setKremsorte(line[7]);

                kTemp = ot;

                if (line.length >= 9) {

                    for (int i = 1; i < line.length / 5; i++) {
                        BigDecimal bPreis = new BigDecimal(Double.parseDouble(commaPreisInput(line[i * kLength + 3])));
                        int bNaehrwert = Integer.parseInt(line[i * kLength + 4]);
                        Duration bHaltbarkeit = Duration.ofDays(Long.parseLong(line[i * kLength + 5]));
                        HashSet<Allergen> bAllergene = determineAllergene(line[i * kLength + 6]);
                        String bName = line[i * kLength + 7];


                        kTemp = new Belag(bName, bPreis, bNaehrwert, bHaltbarkeit, bAllergene, ot, kTemp);
                    }
                }
                kFinal = kTemp;

                automat.add(kFinal);
                return true;
            } else {
                throw new InputMismatchException("Gibts kein Art dieser Kuchen.");
            }
        }
        else
            throw new InputMismatchException("Gibts kein Art dieser Kuchen.");
    }

    public boolean addModeTest(String eingabe)
    {
        String[] line = eingabe.split(" ");
        System.out.println(Arrays.toString(line));
        System.out.println(eingabe);


        String[] allergeneStrings;
        HashSet<Allergen> allergene = new HashSet<Allergen>();

        if (line == null) {
            throw new NullPointerException("Kann nicht null einfuegen");
        }
        //Herstellername
        if (line.length == 1) {
            automat.addHersteller(new Hersteller(line[0]));
            return true;
        } else if (line.length >= 6) {
            if (!line[5].equals(",")) {
                allergeneStrings = line[5].split(",");

                for (int i = 0; i < allergeneStrings.length; i++) {
                    for (Allergen loop : Allergen.values()) {
                        if ((loop.toString()).equals(allergeneStrings[i])) {
                            allergene.add(loop);
                        }
                    }
                }
            }

            if (line[0].equals("Kuchen")) {
                Kuchen k = new Kuchen();

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        k.setHersteller((Hersteller) h);
                    }
                }

                k.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                k.setNaehrwert(Integer.parseInt(line[3]));
                k.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                k.setAllergene(allergene);
                k.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(k);
                return true;
            } else if (line[0].equals("Kremkuchen")) {
                Kremkuchen kk = new Kremkuchen();

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        kk.setHersteller((Hersteller) h);
                    }
                }
                kk.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                kk.setNaehrwert(Integer.parseInt(line[3]));
                kk.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                kk.setAllergene(allergene);
                kk.setKremsorte(line[6]);
                kk.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(kk);
                return true;
            } else if (line[0].equals("Obstkuchen")) {
                Obstkuchen ok = new Obstkuchen();
                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        ok.setHersteller((Hersteller) h);
                    }
                }
                ok.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                ok.setNaehrwert(Integer.parseInt(line[3]));
                ok.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ok.setAllergene(allergene);
                ok.setObstsorte(line[6]);
                ok.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(ok);
                return true;
            } else if (line[0].equals("Obsttorte")) {
                Obsttorte ot = new Obsttorte();

                for (Object h : automat.getHerstellern()) {
                    if (line[1].equals(((Hersteller) h).getName())) {
                        ot.setHersteller((Hersteller) h);
                    }
                }
                ot.setPreis(new BigDecimal(Double.parseDouble(commaPreisInput(line[2]))));
                ot.setNaehrwert(Integer.parseInt(line[3]));
                ot.setHaltbarkeit(Duration.ofDays(Long.parseLong(line[4])));
                ot.setAllergene(allergene);
                ot.setObstsorte(line[6]);
                ot.setKremsorte(line[7]);
                ot.setFachnummer(ThreadLocalRandom.current().nextInt(0, 10000));

                automat.add(ot);
                return true;
            } else {
                throw new InputMismatchException("Gibts kein Art dieser Kuchen.");
            }


        } else
            throw new InputMismatchException(eingabe +" "+ Arrays.toString(line) + " Ungueltige Eingabe.");


    }

    public void deleteMode() throws InputMismatchException {
        System.out.println("-- LoeschModus --");
        System.out.println("[Hersteller] - Loescht den Produzenten");
        System.out.println("[Fachnummer] - Entfernt den Kuchen\n");

        scnr.nextLine();

        if (scnr.hasNextInt()) {
            IntInputEvent intIn = new IntInputEvent(this, scnr.nextInt());
            if (this.getConsoleHandler() != null)
                getAutomat().removeKuchen(getConsoleHandler().handleIntInput(intIn));
            return;

        } else if (scnr.hasNext()) {
            InputEvent stringIn = new InputEvent(this, scnr.next());
            if (this.consoleHandler != null)
                automat.removeHersteller(consoleHandler.handleInput(stringIn));
            return;
        }
        throw new InputMismatchException("Keine gueltige Hersteller oder Fachnummer.");
    }

    public void displayMode() throws InputMismatchException {
        System.out.println("-- AnzeigeModus --");
        System.out.println("hersteller - Anzeige der Hersteller mit der Anzahl der Kuchen");
        System.out.println("kuchen [Typ] - Anzeige der Kuchen gefiltert nach Typ");
        System.out.println("allergene i/e - Anzeige der vorhandenen / nicht vorhandenen Allergene\n");

        scnr.nextLine();

        String[] line = {};
        ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);

        if (this.consoleHandler != null) {
            consoleHandler.handleArrayInput(input);
        }

        line = input.getArray();

        for (int i = 0; i < line.length; i++) {
            if (line[0].equals("hersteller")) {
                System.out.println(automat.listHersteller());
                return;
            } else if (line[0].equals("kuchen")) {
                if (line[1].equals("Kuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(2));
                    return;
                } else if (line[1].equals("Kremkuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(3));
                    return;
                } else if (line[1].equals("Obstkuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(4));
                    return;
                } else if (line[1].equals("Obsttorte")) {
                    System.out.println(automat.listVerkaufsobjekte(5));
                    return;
                } else {
                    throw new InputMismatchException("Falsche Eingabe.");
                }
            } else if (line[0].equals("allergene")) {
                if (line[1].equals("i")) {
                    System.out.println(automat.listAllergene(1));
                    return;
                } else if (line[1].equals("e")) {
                    System.out.println(automat.listAllergene(2));
                    return;
                } else
                    throw new InputMismatchException("Falsche Eingabe.");
            } else
                throw new InputMismatchException("Falsche Eingabe.");
        }
    }

    public boolean displayModeTest(String eingabe) throws InputMismatchException
    {
        String[] line = eingabe.split(" ");


        for (int i = 0; i < line.length; i++) {
            if (line[0].equals("hersteller") && line.length == 1)
            {
                System.out.println(automat.listHersteller());
                return true;
            } else if (line[0].equals("kuchen")) {
                if (line[1].equals("Kuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(2));
                    return true;
                } else if (line[1].equals("Kremkuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(3));
                    return true;
                } else if (line[1].equals("Obstkuchen")) {
                    System.out.println(automat.listVerkaufsobjekte(4));
                    return true;
                } else if (line[1].equals("Obsttorte")) {
                    System.out.println(automat.listVerkaufsobjekte(5));
                    return true;
                } else {
                    throw new InputMismatchException("Falsche Eingabe.");
                }
            } else if (line[0].equals("allergene")) {
                if (line[1].equals("i")) {
                    System.out.println(automat.listAllergene(1));
                    return true;
                } else if (line[1].equals("e")) {
                    System.out.println(automat.listAllergene(2));
                    return true;
                } else
                    throw new InputMismatchException("Falsche Eingabe.");
            } else
                throw new InputMismatchException("Falsche Eingabe.");
        }
        return false;
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

            IntInputEvent intIn = new IntInputEvent(this,scnr.nextInt());
            if(this.consoleHandler != null)
                fachnummer = consoleHandler.handleIntInput(intIn);

            System.out.println("[Jahr] [Monat] [Tag] - setzt das Datum");
            System.out.println("2001 6 31 - z.B.");
            if(scnr.hasNextInt())
            {
                scnr.nextLine();

                String[] line = {};
                ArrayInputEvent input = new ArrayInputEvent(this, scnr.nextLine(), line);
                if(this.consoleHandler != null)
                    consoleHandler.handleArrayInput(input);

                line = input.getArray();

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
                eingabe = consoleHandler.handleInput(stringIn);

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

                notifyObserversCapacity();
                notifyObserversAllergene();
            }
    }

    public String commaPreisInput(String input)
    {
        String vor = "";
        String nach = "";
        int count = 0;

        if(input.contains(","))
        {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == (',')) {
                    count++;
                }
            }

            String[] preis = input.split(",");

            if (count == 1) {
                vor = preis[0];
                nach = preis[1];
                return vor + "." + nach;
            } else
                throw new InputMismatchException("Falsche Preis Eingabe.");

        }
        else
            return input;

    }

    public HashSet<Allergen> determineAllergene(String input)
    {
        String[] allergeneStrings;
        HashSet<Allergen> allergene = new HashSet<Allergen>();

        if (!input.equals(",")) {
            allergeneStrings = input.split(",");

            for (int i = 0; i < allergeneStrings.length; i++) {
                for (Allergen loop : Allergen.values()) {
                    if ((loop.toString()).equals(allergeneStrings[i])) {
                        allergene.add(loop);
                    }
                }
            }
        }

        return allergene;
    }

    public void notifyObserversCapacity()
    {
        for(main.CLI.observerPattern.Observer o : getList())
        {
            if(o instanceof CapacityObserver)
            {
                o.update();
            }
        }
    }


    public void notifyObserversAllergene()
    {
        for(Observer o : getList())
        {
            if(o instanceof AllergenObserver)
            {
                o.update();
            }
        }
    }
}
