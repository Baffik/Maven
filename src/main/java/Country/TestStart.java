package Country;

import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static main.java.Country.Start.CreateIdentifikator;
import static main.java.Country.Start.Vremya;
import static main.java.Country.Start.VuvodPriv;

/**
 * Created by Nika on 31.08.2017.
 */
public class TestStart {

    String gorod = "New York";
    String continent = "America";

    @Test
    public void TestCreateIdentifikator() {
        String test = CreateIdentifikator(gorod, continent);
        Assert.assertTrue("", test.equals("America/New_York"));
    }

    @Test
    public void TestVremya() {

        String test = "America/New_York";
        double time = Vremya(test);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(test));
        String sDate = dateFormat.format(new Date(System.currentTimeMillis()));
        sDate = sDate.replace(':', '.');
        double time1 = Double.parseDouble(sDate);

        Assert.assertTrue(time == time1);
    }

    @Test
    public void TestVremyaGMT() {

       String test = "Dnipro";
        double time = Vremya(test);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00"));
        String sDate = dateFormat.format(new Date(System.currentTimeMillis()));
        sDate = sDate.replace(':', '.');
        double time1 = Double.parseDouble(sDate)+1;

        Assert.assertTrue(time == time1);
    }

    @Test
    public void TestVuvodPriv() {

        Locale usa = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("text", usa);
        double time = 22.0;
        String text = VuvodPriv(bundle, gorod, time);

        Assert.assertTrue( text.equals("Good evening, New York"));

    }
}


