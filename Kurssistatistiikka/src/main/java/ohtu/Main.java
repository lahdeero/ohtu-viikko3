package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String bodyText2 = Request.Get(courseUrl).execute().returnContent().asString();
        
        
        String url3 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats"; // palvelimelta haettu opiskelijoiden palautusstatistiikka
        String statsResponse = Request.Get(url3).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(bodyText2, Course[].class); 
        
        System.out.println("Opiskelijanumero " + studentNr + "\n");
        
        for (Course course : courses) {
            course.initParsetData();
            for (Submission sub : subs) {
                if (sub.getCourse().equals(course.getName())) {
                    course.addSubmission(sub);
                    sub.setMaxexercises(course.getExercises()[sub.getWeek()]);
                }
            }
        }
        
        for (Course course : courses) {
            System.out.println(course.toString());
        }

    }
}