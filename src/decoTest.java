import main.GL.Automat;
import main.GL.Hersteller;
import main.GL.Kremkuchen;
import main.GL.dekorator.Belag;
import main.GL.interfaces.Allergen;
import main.GL.interfaces.Kuchenbar;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class decoTest
{
    public static void main(String[] args)
    {
        ArrayList<Allergen> hs = new ArrayList<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);
        ArrayList<Allergen> ge = new ArrayList<Allergen>();
        ArrayList<Allergen> m = new ArrayList<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);
        m.add(Allergen.Milch);
        Hersteller jacobs = new Hersteller("Jacobs");
        Automat auto = new Automat(5);
        Kremkuchen kk = new Kremkuchen(new BigDecimal(1), new Date(2021, 04, 22), jacobs, 250, hs, Duration.ofDays(8), "Sahne");
        System.out.println(kk.toString());
        Belag cherry = new Belag("Cherry", new BigDecimal(2), 50, Duration.ofDays(3), m, kk);
        System.out.println(cherry.toString());
        Kuchenbar k = new Belag("Berry", new BigDecimal(3), 50, Duration.ofDays(5), ge, cherry);
        Kuchenbar kkk = new Belag("Berry", new BigDecimal(3), 50, Duration.ofDays(5), ge, k);

        auto.add(kkk);

        System.out.println(auto.listVerkaufsobjekte(1));
    }
}
