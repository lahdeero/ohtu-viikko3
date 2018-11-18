package ohtu;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;


public class Course {
    private String id;
    private String name;
    private String url;
    private int week;
    private boolean enabled;
    private String term;
    private int year;
    private int __v;
    private String fullName;
    private boolean miniproject;
    private int[] exercises;
    List<Submission> subs;
    private int doneExercises;
    private int totalHours;
    
    private int totalSubmissions;
    private int submittedExercises;
    private int submissionTotalHours;
    
    private JsonObject parsetData;
    
    public Course() {
        subs = new ArrayList();
    }
    
    public void initParsetData() throws IOException {
        JsonParser parser = new JsonParser();
        String url3 = "";
        if (name.equals("ohtu2018")) url3 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        if (name.equals("rails2018")) url3 = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        if (url3.isEmpty()) return;
        String statsResponse = Request.Get(url3).execute().returnContent().asString();
        parsetData = parser.parse(statsResponse).getAsJsonObject();

        this.parsetData = parsetData;
        int foo = 1;
        while(parsetData.getAsJsonObject(""+foo) != null) {
            JsonObject wk = parsetData.getAsJsonObject(""+ foo++);
            totalSubmissions += Integer.parseInt(wk.get("students").toString());
            submittedExercises += Integer.parseInt(wk.get("exercise_total").toString());
            submissionTotalHours += (int)Double.parseDouble(wk.get("hour_total").toString());
        }
    }
    
    public void addSubmission(Submission sub) {
        subs.add(sub);
        doneExercises += sub.getExercises().length;
        totalHours += sub.getHours();
    }
    
    public String getName() {
        return name;
    }
    public int[] getExercises() {
        return exercises;
    }
    
    @Override
    public String toString() {
        if (subs.size() < 1) return "";
        String ret = "";
        for (Submission sub : subs) {
            ret += sub.toString() + "\n";
        }
        int totalExercises = 0;
        for (int luku : exercises) totalExercises += luku;
        return ""+fullName+" "+term+" "+year+"\n\n"
                + ""+ret+"\nyhteensa: "+
                doneExercises+"/"+totalExercises+" tehtavaa " + totalHours + " tuntia\n\n"+
                "kurssilla yhteensa " + totalSubmissions + " palautusta, palautettuja tehtavia " + 
                submittedExercises + " kpl, aikaa kaytetty yhteensa " + submissionTotalHours + " tuntia\n";
    }

}
