package com.example.tornado;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JokeAPIActivity extends AppCompatActivity {

    private TextView mTextViewRes, mTextViewAns;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_api);

        ImageView back = findViewById(R.id.back_btn_joke);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTextViewRes = findViewById(R.id.text_jokes);

        final Button buttonParse = findViewById(R.id.btn_random_joke);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    buttonParse.setClickable(false);
                    jsonParse();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            buttonParse.setClickable(true);
                        }
                    }, 3000);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void jsonParse() throws JSONException {
        String url = "https://blague.xyz/api/vdm/random";
        String url2 = "https://api-light.com/api/random";

        String wanted = "?limit=1&category=blague";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url2 + wanted,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //JSONArray jsonArray = response.getJSONArray("");
                    JSONObject jsonObject = response.getJSONObject(0);
                    //System.out.println(jsonArray.toString());
                    String content = jsonObject.getString("content");

                    mTextViewRes.setText(content);
                    System.out.println(response.toString());
                } catch (JSONException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                error.printStackTrace();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization: Bearer", getResources().getString(R.string.tokenJoke));

                return params;
            }
        };
        mQueue.add(request);
    }

}
