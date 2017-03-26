package fr.esilv.s8.finalproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.finalproject.Constants;
import fr.esilv.s8.finalproject.R;
import fr.esilv.s8.finalproject.adapters.VideosAdapter;
import fr.esilv.s8.finalproject.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.finalproject.models.Videos;

public class MainActivity extends AppCompatActivity {

    private static final String SEARCH_URL="https://www.googleapis.com/youtube/v3/search?part=snippet&q=";
    private static final String SEARCH_URL_NEXT="&type=video&videoCaption=closedCaption&key=";

    private EditText areaSearch;
    private Button buttonSearch;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    areaSearch = (EditText) findViewById(R.id.areaSearch);
    buttonSearch = (Button) findViewById(R.id.buttonSearch);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    buttonSearch.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //exécuter la recherche avec le(s) mot(s) de areaSearch.getText().toString()
            String search=areaSearch.getText().toString();

            getVideo(search);
        }

    });
}

    private void getVideo(String search) {
        StringRequest videosRequest = new StringRequest(SEARCH_URL + search + SEARCH_URL_NEXT + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //parse data from webservice to get Contracts as Java object
                Videos videos = new Gson().fromJson(response, Videos.class);

                setAdapter(videos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });

        Volley.newRequestQueue(this).add(videosRequest);
    }

    private void setAdapter(Videos videos) {
        VideosAdapter adapter = new VideosAdapter(videos);
        adapter.setOnVideoSelectedListener((OnVideoSelectedListener) this);
        recyclerView.setAdapter(adapter);
    }

 /*   @Override
    public void onContractSelected(Video video) {
        startActivity(new Intent(this, CelluleActivity.class));
//		StationsActivity.start(this, contract.getName());
    }
*/
}
