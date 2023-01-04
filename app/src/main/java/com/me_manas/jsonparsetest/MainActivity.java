package com.me_manas.jsonparsetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // creating variables for our textview,
    // imageview,cardview and progressbar.
    private TextView courseNameTV, courseTracksTV, courseBatchTV;
    private ImageView courseIV;
    private ProgressBar loadingPB;
    private CardView courseCV;

    String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingPB=findViewById(R.id.idLoadingPB);
        courseCV = findViewById(R.id.idCVCourse);
        courseNameTV = findViewById(R.id.idTVCourseName);
        courseTracksTV = findViewById(R.id.idTVTracks);
        courseBatchTV = findViewById(R.id.idTVBatch);
        courseIV = findViewById(R.id.idIVCourse);

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside the on response method.
                // we are hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // in below line we are making our card
                // view visible after we get all the data.
                courseCV.setVisibility(View.VISIBLE);
                try {
                    // now we get our response from API in json object format.
                    // in below line we are extracting a string with its key
                    // value from our json object.
                    // similarly we are extracting all the strings from our json object.
//                    String courseName = response.getString("time");
//                    JSONArray arr= response.getJSONArray("time");
//                    String courseName = arr.getString(0);
//                    Log.d("^^^^^",courseName);

                        JSONObject jsn=(response.getJSONObject("time"));
                        String courseName = jsn.getString("updated");
//                    String courseTracks = response.getString("courseTracks");
//                    String courseMode = response.getString("courseMode");
//                    String courseImageURL = response.getString("courseimg");

                    // after extracting all the data we are
                    // setting that data to all our views.
                    courseNameTV.setText(courseName);
//                    courseTracksTV.setText(courseTracks);
//                    courseBatchTV.setText(courseMode);

                    // we are using picasso to load the image from url.
//                    Picasso.get().load(courseImageURL).into(courseIV);
                } catch (JSONException e) {
                    // if we do not extract data from json object properly.
                    // below line of code is use to handle json exception
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("#*#*#*#*#* ", "Entered with error in request");
                Toast.makeText(MainActivity.this,"Fail to obtain info...",Toast.LENGTH_LONG).show();
            }
        });

                queue.add(jsonObjectRequest);
    }
}