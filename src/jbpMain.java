import main.GL.Automat;
import main.GL.Hersteller;
import main.GL.Kremkuchen;
import main.GL.Obstkuchen;
import main.GL.interfaces.Allergen;
import main.IO.jbp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class jbpMain
{
    public static void main(String[] args)
    {
        Automat auto = new Automat(5);
        Hersteller j = new Hersteller("Johnsons");
        ArrayList<Allergen> hs = new ArrayList<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);

        Obstkuchen obstkuchen = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), j, 250, hs, Duration.ofDays(5), "Erdbeer");
        Kremkuchen kremkuchen = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), j, 250, hs, Duration.ofDays(5), "Sahne");

        auto.addHersteller(j);
        auto.add(obstkuchen);
        auto.add(kremkuchen);
        System.out.println(auto.listVerkaufsobjekte(1));


        jbp.saveAutomat("jbp.xml", auto);
        System.out.println("------------------");
        Automat loaded = jbp.loadAutomat("jbp.xml");
        System.out.println(loaded.listVerkaufsobjekte(1));



    }
}
