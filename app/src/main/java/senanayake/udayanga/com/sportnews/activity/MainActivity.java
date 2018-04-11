package senanayake.udayanga.com.sportnews.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import senanayake.udayanga.com.sportnews.AsyncResponse;
import senanayake.udayanga.com.sportnews.R;
import senanayake.udayanga.com.sportnews.adapter.DataAdapter;
import senanayake.udayanga.com.sportnews.adapter.LoadImage;
import senanayake.udayanga.com.sportnews.model.News;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    String url1 = "https://skysportsapi.herokuapp.com/sky/getnews/";
    String url3 = "/v1.0/";

    ProgressDialog progressDialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cricket:
                    viewNews("cricket");
                    return true;
//                case R.id.navigation_football:
//                    viewNews("football");
//                    return true;
                case R.id.navigation_tenis:
                    viewNews("tennis");
                    return true;
                case R.id.navigation_boxing:
                    viewNews("boxing");
                    return true;
                case R.id.navigation_golf:
                    viewNews("golf");
                    return true;
                case R.id.navigation_horse_racing:
                    viewNews("horse-racing");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewNews("cricket");

        ListView listView = findViewById(R.id.listView);
        onListItemClick(listView);
    }

    private void viewNews(String sport) {
        progressDialog.show();

        String url = url1 + sport + url3;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                processFinish(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(jsonArrayRequest);

    }

    @Override
    public void processFinish(JSONArray jsonArray) {
        ArrayList<News> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataObject = jsonArray.getJSONObject(i);
                LoadImage loadImage = new LoadImage();
                Bitmap bitmap = loadImage.execute(dataObject.getString("imgsrc")).get();
                arrayList.add(new News(
                        dataObject.getString("shortdesc"),
                        dataObject.getString("title"),
                        "link", "imgsrc", bitmap
                ));

            }
            DataAdapter dataAdapter = new DataAdapter(MainActivity.this, arrayList);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(dataAdapter);
            progressDialog.dismiss();
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void onListItemClick(ListView listView) {

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News news = (News) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("description", news.getDescription());
                intent.putExtra("title", news.getTitle());
                intent.putExtra("bitmap", news.getBitmap());
                startActivity(intent);

            }
        });
    }
}
