


public class ObserverPattern {
    /*
        sollte folgende Ausgabe produzieren:

     */
    /*
    public static void main(String[] args)
    {
        Verkaufsobjekt obj = new Verkaufsobjekt();
        Kuchen kuchen = new Kuchen();
        Kremkuchen krem = new Kremkuchen();
        Obstkuchen obst = new Obstkuchen();
        Automat auto = new Automat(10);

        ObservableCapacity capacity = new ObservableCapacity(0);
        CapacityObserver observer1 = new CapacityObserver(capacity,"O1");
        new CapacityObserver(capacity,"O2");
        capacity.removeObserver(observer1);
        capacity.addObserver(observer1);

        ObservableAllergen allergene = new ObservableAllergen(0);
        AllergenObserver observer2 = new AllergenObserver(allergene,"O3");
        new AllergenObserver(allergene,"O4");
        allergene.removeObserver(observer1);
        allergene.addObserver(observer1);

        ArrayList<Allergen> ge = new ArrayList<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);
        ArrayList<Allergen> hs = new ArrayList<Allergen>();
        ge.add(Allergen.Haselnuss);
        ge.add(Allergen.Sesamsamen);
        ge.add(Allergen.Gluten);

        Kuchen k1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15682, new Hersteller("Herstello"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        Kuchen k2 = new Kuchen(BigDecimal.TEN, new Date(2021, 04, 20), 15672, new Hersteller("Herstelli"), 250, new ArrayList<Allergen>(), Duration.ofDays(4));

        auto.add(k1);
        auto.add(k2);
        auto.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 21), 15662, new Hersteller("Knope"), 250, ge, Duration.ofDays(3)));
        auto.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), 15681, new Hersteller("Knorr"), 250, hs, Duration.ofDays(5)));

        capacity.checkCapacity(auto);
        allergene.checkAllergens(auto);

        auto.add(new Verkaufsobjekt(BigDecimal.ONE, new Date(2021, 04, 22), 14681));
        auto.add(new Verkaufsobjekt(BigDecimal.TEN, new Date(2021, 04, 22), 13681));

        capacity.checkCapacity(auto);
        allergene.checkAllergens(auto);

        auto.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 35681, new Hersteller("Jacksons"), 250, hs, Duration.ofDays(5), "Sahne"));

        auto.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24681, new Hersteller("Johnsons"), 250, hs, Duration.ofDays(5), "Erdbeer"));


        capacity.checkCapacity(auto);
        allergene.checkAllergens(auto);
    }
     */
}
