import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * We will have a Website object that gets the data from the site every 10 minutes and notify us if we have a new post available .
 * Created by cordu on 10/5/2018.
 */
public class Website implements Runnable{

    private String stringURL;
    private StringBuffer websiteHTML;
    private List<String> jobTitle;
    private String lastJobPosted;

    /**
     * @param url
     */
    public Website(String url){
        this.stringURL = url;
        websiteHTML = new StringBuffer();
        jobTitle = new ArrayList<>();
        lastJobPosted = "none";
    }

    /**
     * Run method will make the connection and process the data
     */
    @Override
    public void run() {
        // Make a URL to the web page
        while(true) {
            try {
                URL url = new URL(stringURL);

                // Get the input stream through URL Connection
                URLConnection con = url.openConnection();
                InputStream is = con.getInputStream();

                // Once you have the Input Stream, it's just plain old Java IO stuff.

                // I'll use a reader and output the text content to System.out.

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;

                // read each line and write to System.out
                while ((line = br.readLine()) != null) {
                    websiteHTML.append(line);
                    websiteHTML.append("\n");
                }


            } catch (IOException e) {}

            getLastJobs();

            if (lastJobPosted.equals(jobTitle.get(0))) {
                //do nothing
            } else {
                JOptionPane.showMessageDialog(null, "NEW JOB POSTED, CHECK IT OUT");
                lastJobPosted = jobTitle.get(0);
            }
            // Wait 30 minutes and then check again
            try {
                Thread.sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will process the website html and get for us the last jobs posted on it in a List
     */
    private void getLastJobs() {
        for(int i = 0 ; i < 9 ; i++){
            String jobMap = websiteHTML.substring(websiteHTML.lastIndexOf("jobmap[" + i + "]= {jk"),websiteHTML.lastIndexOf("jobmap[" + (i + 1) + "]= {jk"));
            String job = jobMap.substring(jobMap.lastIndexOf("title:'")+7,jobMap.lastIndexOf("',locid"));
            jobTitle.add(job);
        }
    }
}
