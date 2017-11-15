package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "013734198";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String omatUrl = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String kurssinUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String kaikkiPalautuksetUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
        
        String omatBodyText = Request.Get(omatUrl).execute().returnContent().asString();
        String kurssinBodyText = Request.Get(kurssinUrl).execute().returnContent().asString();
        String statsResponse = Request.Get(kaikkiPalautuksetUrl).execute().returnContent().asString();


        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(omatBodyText, Submission[].class);
        Kurssintiedot kurssi = mapper.fromJson(kurssinBodyText, Kurssintiedot.class);
        
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        
        System.out.println(parsittuData);
        System.out.println(parsittuData.get("1").getAsJsonObject().get("students"));
        
        System.out.println("\nKurssi: " + kurssi.getName() + ", " + kurssi.getTerm() + "\n");
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        int tehtYht = 0;
        int tunnitYht = 0;
        for (int i = 0; i < subs.length; i++) {
            System.out.println("viikko " + subs[i].getWeek() + ":\n   tehtyjä tehtäviä yhteensä: " + subs[i].getExercises().length + " (maksimi " + kurssi.getExercises()[i] + ") , aikaa kului " + subs[i].getHours() + " tuntia, tehdyt tehtävät: " + Arrays.toString(subs[i].getExercises()).replaceAll("\\[|\\]", ""));
            tehtYht += subs[i].getExercises().length;
            tunnitYht += subs[i].getHours();
        }
        System.out.println("\nyhteensä: " + tehtYht + " tehtävää, " + tunnitYht + " tuntia\n");
        
        int palautukset = 0;
        int tehtavat = 0;
        for (int i = 1; i <= parsittuData.size(); i++) {
            palautukset += parsittuData.get("" + i).getAsJsonObject().get("students").getAsInt();
            tehtavat += parsittuData.get("" + i).getAsJsonObject().get("exercise_total").getAsInt();
        }
        
        System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä " + tehtavat + " kpl");
        

    }
}
