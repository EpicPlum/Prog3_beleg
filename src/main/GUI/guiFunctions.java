package main.GUI;

import main.GL.*;
import main.GL.dekorator.Belag;
import main.GL.interfaces.*;
import main.IO.jbp;
import main.IO.jos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class guiFunctions
{
    private static Scanner scnr;
    private volatile Automat automat = new Automat(5);

    public Automat getAutomat()
    {
        return automat;
    }

    public void addMode(String input) throws InputMismatchException
    {
        scnr = new Scanner(input);

        String[] line = scnr.nextLine().split(" ");
        String[] allergeneStrings;
        HashSet<Allergen> allergene = new HashSet<Allergen>();

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
            int kLength = 5;

            if(line[0].equals("Kuchen"))
            {
                Kuchen k = new Kuchen();
                Kuchenbar kFinal;
                Kuchenbar kTemp;

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        k.setHersteller((Hersteller)h);
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
            }
            else if(line[0].equals("Kremkuchen"))
            {
                Kremkuchen kk = new Kremkuchen();
                Kremkuchenbar kFinal;
                Kremkuchenbar kTemp;

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        kk.setHersteller((Hersteller)h);
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
            }
            else if(line[0].equals("Obstkuchen"))
            {
                Obstkuchen ok = new Obstkuchen();
                Obstkuchenbar kFinal;
                Obstkuchenbar kTemp;

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        ok.setHersteller((Hersteller)h);
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
            }
            else if(line[0].equals("Obsttorte"))
            {
                Obsttorte ot = new Obsttorte();
                Obsttortebar kFinal;
                Obsttortebar kTemp;

                for(Object h : automat.getHerstellern())
                {
                    if(line[1].equals(((Hersteller)h).getName()))
                    {
                        ot.setHersteller((Hersteller)h);
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
            }
            else
            {
                throw new InputMismatchException("Gibts kein Art dieser Kuchen.");
            }


        }
        else
            throw new InputMismatchException("Ungueltige Eingabe.");

    }

    public void deleteMode(String input) throws InputMismatchException
    {
        scnr = new Scanner(input);

        String eingabe;
        int intEingabe;

        if(scnr.hasNextInt())
        {
            intEingabe = scnr.nextInt();
            automat.removeKuchen(intEingabe);
            return;

        }
        else if(scnr.hasNext())
        {
            eingabe = scnr.next();
            automat.removeHersteller(eingabe);
            return;
        }
        throw new InputMismatchException("Keine gueltige Hersteller oder Fachnummer.");
    }

    public String displayMode(String input) throws InputMismatchException
    {
        String output = "";
        scnr = new Scanner(input);
        String[] line = scnr.nextLine().split(" ");

        for(int i = 0; i < line.length; i++)
        {
            if(line[0].equals("hersteller"))
            {
                output = automat.listHersteller();
                return output;
            }
            else if(line[0].equals("kuchen"))
            {
                if(line[1].equals("Kuchen"))
                {
                    output = automat.listVerkaufsobjekte(2);
                    return output;
                }
                else if(line[1].equals("Kremkuchen"))
                {
                    output = automat.listVerkaufsobjekte(3);
                    return output;
                }
                else if(line[1].equals("Obstkuchen"))
                {
                    output = automat.listVerkaufsobjekte(4);
                    return output;
                }
                else if(line[1].equals("Obsttorte"))
                {
                    output = automat.listVerkaufsobjekte(5);
                    return output;
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
                    output = automat.listAllergene(1);
                    return output;
                }
                else if(line[1].equals("e"))
                {
                    output = automat.listAllergene(2);
                    return output;
                }
                else
                    throw new InputMismatchException("Falsche Eingabe.");
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }
        return output;
    }

    public void inspectionMode(String input)
    {
        scnr = new Scanner(input);
        int fachnummer = 0;
        Date date;
        int month = 0, day = 0, year = 0;

        if(scnr.hasNextInt())
        {
            String[] line = scnr.nextLine().split(" ");

                if (line.length == 4)
                {
                    if (Integer.parseInt(line[0]) > 0 && Integer.parseInt(line[1]) > 0 && Integer.parseInt(line[2]) > 0 && Integer.parseInt(line[3]) > 0)
                    {
                        fachnummer = Integer.parseInt(line[0]);
                        year = Integer.parseInt(line[1]);
                        month = Integer.parseInt(line[2]);
                        day = Integer.parseInt(line[3]);

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

            automat.setInspektionsdatum(fachnummer, date);
        }

        public void persistenceMode(String input)
        {
            scnr = new Scanner(input);
            String eingabe;

            if(scnr.hasNext())
            {
                eingabe = scnr.next();

                if(eingabe.equals("saveJOS"))
                {
                    jos.saveAutomat("jos.ser", automat);
                }
                else if(eingabe.equals("loadJOS"))
                {
                    automat = jos.loadAutomat("jos.ser");
                }
                else if(eingabe.equals("saveJBP"))
                {
                    jbp.saveAutomat("jbp.xml", automat);
                }
                else if (eingabe.equals("loadJBP"))
                {
                    automat = jbp.loadAutomat("jbp.xml");
                }
                else
                    throw new InputMismatchException("Falsche Eingabe.");
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }

    public String commaPreisInput(String input) {
        String vor = "";
        String nach = "";
        int count = 0;

        if (input.contains(",")) {
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

        } else
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

        public int capacity(String input)
    {
        scnr = new Scanner(input);
        int capacity = 0;

        if(scnr.hasNextInt())
        {
            capacity = scnr.nextInt();
        }
        else
            throw new InputMismatchException("Braucht einen Integer.");

        automat.setMaxSize(capacity);
        return capacity;
    }
}
