package codrut.codru.cordu.AnimeRecommendations;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.picasso.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Activitatea2 extends AppCompatActivity {

    private StringBuilder animeURL;
    ListView listView;
    private String[] recomandari = new String[29];
    private String[] descriere = new String[29];
    private String[] hyperLinks = new String[29];
    private String[] imageURL = new String[29];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitatea2);

       listView = findViewById(R.id.listview);


        doStuff();
        backButton();
    }

    public void backButton() {
        ImageButton buton = (ImageButton) findViewById(R.id.imageButton3);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void getURL() {
        String anime = getIntent().getStringExtra("Anime");
        animeURL = new StringBuilder();
        for (int i = 0; i < anime.length(); i++) {
            if (anime.charAt(i) == ' ') {
                animeURL.append('+');
            } else if (anime.charAt(i) < 'A' && anime.charAt(i) != ' ') {
                char ch = anime.charAt(i);
                animeURL.append('%');
                animeURL.append(Integer.toHexString((int) ch));
            } else {
                animeURL.append(anime.charAt(i));
            }
        }
    }

    public void doStuff() {
        getURL();
        new JsonTask().execute("http://animesuggestions.com/?name=" + animeURL.toString());

    }


    class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                System.out.println("ceva");
                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public void getHypertext(String[] recomandari){
            int j = 0;
            for(String a : recomandari){
                StringBuilder rezultat = new StringBuilder();
                for(int i = 0; i < a.length(); i++){
                    if(a.charAt(i)==' '||a.charAt(i)=='('||a.charAt(i)=='/'){
                        rezultat.append('-');
                    }
                    else if(a.charAt(i)=='-'||(a.charAt(i)>64&&a.charAt(i)<123)){
                        rezultat.append(a.charAt(i));
                    }
                }

                hyperLinks[j] = "https://www.anime-planet.com/anime/" + rezultat.toString();
                j++;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null) {
                getAnimes(result);
                if (recomandari[1].contains("<head>") || recomandari == null) {
                    TextView errorText = findViewById(R.id.errorview);
                    errorText.setText("The anime could not be found or your device is not connected to the internet.");
                    errorText.setEnabled(false);
                } else {
                    getHypertext(recomandari);
                    TextView reco = findViewById(R.id.textRecommendations);
                    reco.setEnabled(false);
                    reco.setVisibility(View.VISIBLE);
                    reco.setText("Recommendations :");
                    getAnimes(result);
                    CustomAdapter customAdapter = new CustomAdapter();
                    listView.setAdapter(customAdapter);
                }
            }else{
                TextView errorText = findViewById(R.id.errorview);
                errorText.setText("The anime could not be found or your device is not connected to the internet.");
                errorText.setEnabled(false);
            }
        }
    }

    public void getAnimes(String htmlResult) {
        String animeIncomplet;
        String descriereIncompleta;
        String imagineIncompleta;
        for (int i = 0; i < 29; i++) {
            if (i < 10) {
                animeIncomplet = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 8, htmlResult.lastIndexOf("=" + i + "&name=") +150);
                descriereIncompleta = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 8, htmlResult.lastIndexOf("=" + i + "&name=") + 1500);
                imagineIncompleta = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 8, htmlResult.lastIndexOf("=" + i + "&name=") + 300);
            } else {
                animeIncomplet = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 9, htmlResult.lastIndexOf("=" + i + "&name=") + 150);
                descriereIncompleta = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 9, htmlResult.lastIndexOf("=" + i + "&name=") + 1500);
                imagineIncompleta = htmlResult.substring(htmlResult.lastIndexOf("=" + i + "&name=") + 9, htmlResult.lastIndexOf("=" + i + "&name=") + 300);
            }
            StringBuilder animeComplet = new StringBuilder();
            StringBuilder descriereCompleta = new StringBuilder();
            StringBuilder imagineCompleta = new StringBuilder();
            int j = 0;
            while (animeIncomplet.charAt(j) != '"') {
                animeComplet.append(animeIncomplet.charAt(j));
                j++;
            }
            recomandari[i] = (animeComplet.toString());
            descriereIncompleta = descriereIncompleta.substring(descriereIncompleta.lastIndexOf("flowText") + 10);

            j = 0;
            while (descriereIncompleta.charAt(j) != '<') {
                descriereCompleta.append(descriereIncompleta.charAt(j));
                j++;
            }
            descriere[i] = descriereCompleta.toString();
            imagineIncompleta = imagineIncompleta.substring(imagineIncompleta.lastIndexOf("thumbnail-path=") + 16);
            j = 0;
            while (imagineIncompleta.charAt(j) != '"') {
                imagineCompleta.append(imagineIncompleta.charAt(j));
                j++;
            }
            imageURL[i] = "http://animesuggestions.com/" + imagineCompleta.toString();

        }


    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return recomandari.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView textView = view.findViewById(R.id.idText);
            TextView descriptionView = view.findViewById(R.id.descriptionView);
            ImageView animeImage = view.findViewById(R.id.imageView);
            Button animeLink = view.findViewById(R.id.button);
            animeLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = hyperLinks[i];

                    Intent j = new Intent(Intent.ACTION_VIEW);
                    j.setData(Uri.parse(url));
                    startActivity(j);
                }
            });
            Picasso.get()
                    .load(imageURL[i])
                    .into(animeImage);
            textView.setText(recomandari[i]);
            descriptionView.setText(descriere[i]);

            return view;
        }
    }
}

