package main.java.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * Created by Nika on 29.08.2017.
 * D;\java\Diplom

 */
public class Start {

    static String gorod;
    static String continent;
    static String identifikator;
    static String zona;
    static double time;
    static int    hour = 1000 * 60 * 60;
    static int smechenie;
    static String vuvod;
    private static Logger log = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        gorod = reader.readLine();
        continent = reader.readLine();

        Locale current = Locale.getDefault();
        ResourceBundle bundle1 = ResourceBundle.getBundle("text",current, new UTF8Control());
       // Locale usa = new Locale("en", "US");
       // ResourceBundle bundle2 = ResourceBundle.getBundle("text", usa);

        identifikator = CreateIdentifikator(gorod,continent);
        time = Vremya(identifikator);
        String vuvod = VuvodPriv(bundle1, gorod, time);

        log.info(vuvod);
        System.out.println(vuvod);
        // System.out.println(VuvodPriv(bundle2, gorod, time));

    }

    // sozdaem identifikator
    public static String CreateIdentifikator(String gorod, String continent)
    {
        identifikator = continent+"/"+gorod;
        identifikator = identifikator.replace(' ','_');

        log.info(identifikator);
      //  System.out.println(identifikator);
        return identifikator;

    }

    // nahodim zonu
    public static double Vremya(String identifikator)
    {

        TimeZone tz = TimeZone.getTimeZone(identifikator);
        int rawOffset = tz.getRawOffset();
        smechenie = rawOffset/ hour + 1;

       // System.out.println(smechenie);
       // System.out.println("Текущая TimeZone : " + tz.getID() +
         //       " (" + tz.getDisplayName() + ")\n");

        if(smechenie>0)
        {
            zona = "GMT+"+smechenie;
        }
        else  zona = "GMT"+smechenie;

        // ust vremya po GMT
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(zona));

        // preobrazuem datu v string
        String sDate = dateFormat.format(new Date(System.currentTimeMillis()));
        //System.out.println(sDate);

        sDate = sDate.replace(':','.');

        time = Double.parseDouble(sDate);
        System.out.println(time);

        log.info(String.valueOf(time));
        return time;
    }

    public static String VuvodPriv(ResourceBundle bundle, String gorod, double time)
    {

            if (time >= 6.00 && time <= 9.00) {
                String key = bundle.getString("Utro");
                vuvod = key + gorod;
            } else if (time > 9.00 && time <= 19.00) {
                String key = bundle.getString("Den");
                vuvod = key + gorod;
            } else if (time > 19.00 && time <= 23.00) {
                String key = bundle.getString("Vecher");
                vuvod = key + gorod;
            } else if (time > 23.00 || time < 6.00) {
                String key = bundle.getString("Noch");
                vuvod = key + gorod;
            }

        return vuvod;
    }
}
