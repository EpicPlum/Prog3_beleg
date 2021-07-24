package main.GL;

import main.GL.interfaces.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;

/*
Automatobjekt
Implementiert SinglyLinkedList mit verschiedenen Funktion / Methoden
Kann Verkaufsobjekte und Kuchen enthalten
 */

public class Automat implements Serializable
{
    /*
    Instanzvariablen
     */
    private Node head;
    private volatile int size;
    private volatile int maxSize;
    private HashSet<Hersteller> herstellern = new HashSet<Hersteller>();
    private ArrayList<Integer> fachNummern = new ArrayList<Integer>();
    private int letzteFachnummer = 0;
    private int numAllergene = 0;
    @Serial
    private static final long serialVersionUID = 1L;
    /*
    Default Konstruktor
     */
    public Automat()
    {
        head = null;
        size = 0;
        maxSize = 5;
    }
    //Konstruktor fuer gewuenschte Kapazitaet
    public Automat(int maxSize)
    {
        head = null;
        size = 0;
        setMaxSize(maxSize);
    }

    public Node getHead()
    {
        return head;
    }
    /*
    Laenge der List / Automat
     */
    public int size()
    {
        return size;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
    public int maxSize()
    {
        return maxSize;
    }
    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
    }
    /*
    herstellern Getter
     */
    public HashSet<Hersteller> getHerstellern()
    {
        return herstellern;
    }
    /*
    fachNummern Getter
     */
    public ArrayList<Integer> getFachNummern()
    {
        return fachNummern;
    }
    /*
    Allergene Getter
     */
    public int getNumAllergene()
    {
        return numAllergene;
    }
    /*
        Fuegt verkaufsobjekte und automat.Kuchen ein
     */
    public void add(Verkaufsobjektbar added) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException
    {
        if(added == null)
        {
            throw new NullPointerException("Neue Element darf nicht null sein.");
        }
        if(isFull())
        {
            throw new IndexOutOfBoundsException("Automat ist voll.");
        }

        added.setFachnummer(letzteFachnummer);


        //macht eine List der fachNummern
        for(int i = 0; i < getFachNummern().size(); i++)
        {
            if(getFachNummern().get(i).equals(added.getFachnummer()))
            {
                throw new IllegalArgumentException("Gibts dieser Fachnummer schon im Automat.");
            }
        }

        //prueft ob es gibt ein Hersteller schon
        // dann increments
        if(added instanceof Kuchenbar)
        {
            if (herstellern.contains(((Kuchenbar) added).getHersteller()))
            {
                ((Kuchenbar) added).getHersteller().incrementCountKuchen(1);
            }
            else
                throw new IllegalArgumentException("Um ein Kuchen einzufuegen, brauchen Sie einen Hersteller zuerst.");
        }

        Node temp = new Node(added);
        temp.next = null;

        //wenn Automat leer ist
        if(head == null)
        {
            head = temp;
        }
        else
        {
            //wenn Automat nicht leer ist
            Node loop = head;

                while (loop.next != null)
                {
                    loop = loop.next;
                }
                loop.next = temp;


        }

        //added.setFachnummer(letzteFachnummer);
        fachNummern.add(added.getFachnummer());
        letzteFachnummer++;
        //setInspektionsdatum(added, new Date(05112021));
        size++;

    }

    public void addHersteller(Hersteller hersteller)
    {
        if(herstellern.isEmpty())
        {
            herstellern.add(hersteller);
        }
        else
        {
            try {
                for (Hersteller h : herstellern) {
                    if ((h.getName().equals(hersteller.getName()))) {
                        return;
                    } else
                        herstellern.add(hersteller);
                }
            }
            catch(ConcurrentModificationException e)
            {
                addHersteller(hersteller);
            }
        }
    }
    /*
    Entfernt ein automat.Kuchen
     */
    public void removeKuchen(int fachnummer) throws IllegalArgumentException, IndexOutOfBoundsException
    {
        //positiv
        if(fachnummer < 0)
        {
            throw new IllegalArgumentException("Fachnummer darf nicht negativ sein.");
        }
        //nicht leer
        if(isEmpty())
        {
            throw new IndexOutOfBoundsException("Automat ist leer");
        }

        Node temp = head;
        Node vor = null;

        while(temp != null && temp.data.getFachnummer() == fachnummer)
        {
            if(temp.data.getFachnummer() == fachnummer)
            {
                if(temp.data instanceof Kuchenbar)
                {
                    ((Kuchenbar) temp.data).getHersteller().decrementCountKuchen(1);
                }
                //System.out.println(listHersteller());
            }

            head = temp.next;
            temp = head;
        }

        temp = head;

        if(temp != null && temp.data.getFachnummer() == fachnummer)
        {
            head = temp.next;
        }


        while(temp != null && temp.data.getFachnummer() != fachnummer)
        {
            vor = temp;
            temp = temp.next;
        }

        if(temp == null)
        {
            size--;
            return;
        }

        vor.next = temp.next;

        for(int i = 0; i < fachNummern.size(); i++)
        {
            if(fachnummer == fachNummern.get(i))
            {
                fachNummern.remove(i);
            }
        }

        size--;

    }
    //Entfernt einen Hersteller
    public void removeHersteller(String name) throws NullPointerException
    {
        Node loop = head;

        if(name == null)
        {
            throw new NullPointerException("name ist null.");
        }

        if(herstellern.size() == 0)
        {
            return;
        }

        while(loop != null)
        {
            if(((Kuchenbar)loop.data).getHersteller().getName().equals(name))
            {
                removeKuchen(((Kuchenbar) loop.data).getFachnummer());
            }

            loop = loop.next;
        }

        Object[] hArray = herstellern.toArray();
        //einfacht loopt durch
        for(int i = 0; i < herstellern.size(); i++)
        {
            if(((Hersteller)hArray[i]).getName().equals(name))
            {
                herstellern.remove(((Hersteller)hArray[i]));
            }
        }
    }

    /*
        Wahrscheinlich gibts bessere Implementierung mit getClass() / instanceof
        1 = Verkaufsobjekt
        2 = Kuchen
        3 = Kremkuchen
        4 = Obstkuchen
        5 = Obsttorte
     */
    public String listVerkaufsobjekte(int option)
    {
        Node loop = head;
        String print = "";

        while(loop != null)
        {
            switch(option)
            {
                case 1:
                    if(loop.data instanceof Verkaufsobjektbar)
                    {
                        print += "*** " + loop.data.toString() + " ***\n";
                    }
                    break;
                case 2:
                    if(loop.data instanceof Kuchenbar)
                    {
                        print += "*** " + loop.data.toString() + " ***\n";
                    }
                    break;
                case 3:
                    if(loop.data instanceof Kremkuchenbar)
                    {
                        print += "*** " + loop.data.toString() + " ***\n";
                    }
                    break;
                case 4:
                    if(loop.data instanceof Obstkuchenbar)
                    {
                        print += "*** " + loop.data.toString() + " ***\n";
                    }
                    break;
                case 5:
                    if(loop.data instanceof Obsttortebar)
                    {
                        print += "*** " + loop.data.toString() + " ***\n";
                    }
                    break;
            }
            loop = loop.next;
        }
        return print;
    }
    /*
    Abruf aller Hersteller mit der Anzahl der ihrer automat.Kuchen
     */
    public String listHersteller()
    {
        String print = "";
        Node loop = head;
        Object[] hArray = herstellern.toArray();

        if(herstellern.size() == 0)
        {
            return "Keine Herstellern";
        }
        for(int i = 0; i < hArray.length; i++)
        {
            print += ((Hersteller)hArray[i]).getName() + " ";
            print += ((Hersteller)hArray[i]).countKuchen() + "\n";
        }

        return print;
    }
    /*
    Prueft ob Automat leer ist
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    public boolean isFull()
    {
        return size == maxSize;
    }
    /*
    Abruf aller vorhandenen Allergene im Automaten
    Macht eine Summe von aller Allergene im Automat, numAllergene
    option = 1, Vorhandene Allergene
    option = 2, nicht Vorhandene Allergene
     */
    public String listAllergene(int option)
    {
        Node temp = head;
        String print = "";
        HashSet<String> vorhandAllergene = new HashSet<String>();
        EnumSet<Allergen> nichtVorhandAllergene = EnumSet.allOf(Allergen.class);

        while(temp != null && temp.data instanceof Kuchen)
        {
            Object[] currAllergen = ((Kuchenbar)temp.data).getAllergene().toArray();

            for(int i = 0; i < currAllergen.length; i++)
            {
                vorhandAllergene.add(currAllergen[i].toString());
                nichtVorhandAllergene.remove(currAllergen[i]);
            }

            temp = temp.next;
        }
        if(option == 1)
        {
            print = "Vorhandene Allergene im Automat: " + vorhandAllergene.toString();
        }
        else if(option == 2)
        {
            print = "Nicht vorhandene Allergene im Automat: " + nichtVorhandAllergene.toString();
        }
        else
        {
            throw new IllegalArgumentException("Falsche Modus / Option.");
        }

        return print;
    }
    //finds num of vorhandene Allergene im Automat
    public int findNumAllergene()
    {
        Node temp = head;
        HashSet<String> vorhandAllergene = new HashSet<String>();
        EnumSet<Allergen> nichtVorhandAllergene = EnumSet.allOf(Allergen.class);


        while(temp != null && temp.data instanceof Kuchen)
        {
            Object[] currAllergen = ((Kuchenbar)temp.data).getAllergene().toArray();
            for(int i = 0; i < currAllergen.length; i++)
            {
                vorhandAllergene.add(currAllergen[i].toString());
                nichtVorhandAllergene.remove(currAllergen[i]);
            }
            temp = temp.next;
        }


        numAllergene = vorhandAllergene.size();
        return vorhandAllergene.size();
    }

    //setzt inspektionsdatum, benutzt heutige Datum
    public String setInspektionsdatum(int fachnummer, Date date) throws NullPointerException
    {
        Node loop = head;
        String inspected = "Nicht im Automat";

        if(date == null)
        {
            throw new NullPointerException("Objekt darf nicht null sein.");
        }

        while(loop != null)
        {
            if(((Kuchenbar)loop.data).getFachnummer() == fachnummer)
            {
                ((Kuchenbar) loop.data).setInspektionsdatum(date);
                inspected = loop.data.toString();
            }
            loop = loop.next;
        }

        return inspected;
    }

    public boolean fachnummerSort()
    {
        Node curr = head;
        Node loop = null;
        int temp;


        if(isEmpty())
        {
            return false;
        }

        while(curr != null)
        {
            loop = curr.next;

            while(loop != null)
            {
                if(curr.data.getFachnummer() > loop.data.getFachnummer())
                {
                    temp = curr.data.getFachnummer();
                    curr.data.setFachnummer(loop.data.getFachnummer());
                    loop.data.setFachnummer(temp);
                }
                loop = loop.next;
            }
            curr = curr.next;
        }

        return true;
    }

    public boolean herstellerSort()
    {
        Node curr = head;
        Node loop = null;
        Hersteller temp;

        if(isEmpty())
        {
            return false;
        }

        while(curr != null)
        {
            loop = curr.next;

            while(loop != null)
            {
                if(((Kuchenbar)curr.data).getHersteller().compareTo(((Kuchenbar)loop.data).getHersteller()) > 0)
                {
                    temp = ((Kuchenbar)curr.data).getHersteller();
                    ((Kuchenbar)curr.data).setHersteller(((Kuchenbar)loop.data).getHersteller());
                    ((Kuchenbar)loop.data).setHersteller(temp);
                }
                loop = loop.next;
            }
            curr = curr.next;
        }

        return true;
    }

    public boolean haltbarkeitSort()
    {
        Node curr = head;
        Node loop = null;
        Duration temp;

        if(isEmpty())
        {
            return false;
        }

        while(curr != null)
        {
            loop = curr.next;

            while(loop != null)
            {
                if(((Kuchenbar)curr.data).getHaltbarkeit().compareTo(((Kuchenbar)loop.data).getHaltbarkeit()) > 0)
                {
                    temp = ((Kuchenbar)curr.data).getHaltbarkeit();
                    ((Kuchenbar)curr.data).setHaltbarkeit(((Kuchen)loop.data).getHaltbarkeit());
                    ((Kuchenbar)loop.data).setHaltbarkeit(temp);
                }
                loop = loop.next;
            }
            curr = curr.next;
        }

        return true;
    }

}
