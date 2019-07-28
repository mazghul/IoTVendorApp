package com.smartcard.iotaspirantsvendor;


import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.smartcard.iotaspirantsvendor.data.model.AbstractResponse;
import com.smartcard.iotaspirantsvendor.provider.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    String[] keys;
    private  Map<String, String> objects;


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        return listFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        // Inflate the layout for this fragment
        mRequestQueue = Volley.newRequestQueue(getActivity());


/*        VolleyRequest.getProducts(new Response.Listener<AbstractResponse>() {
            @Override
            public void onResponse(AbstractResponse response) {
                Log.d("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }
    )    .enqueue(mRequestQueue);*/
        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://sv91arbpo2.execute-api.us-west-2.amazonaws.com/default/getItems",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                //get a fake property from response
                String title = response.optString("title");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                //handle errors
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //get request headers
                return null;
            }
        };*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://sv91arbpo2.execute-api.us-west-2.amazonaws.com/default/getItems",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON

                        Type type = new TypeToken<Map<String, String>>() {
                        }.getType();

                        objects = new Gson().fromJson(response.toString(), type);
                        Log.d("obj", objects.toString());
                        keys = ((LinkedTreeMap<String, String>) objects).keySet().toArray(new String[0]);

                        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(generateData());
                        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
                                {
                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if(isValueX && value != 0) {
                                            return keys[(int) (value-1)];
                                        }
                                        return super.formatLabel(value, isValueX);
                                    }
                                }
                        );
                        graph.getViewport().setXAxisBoundsManual(true);
                        graph.getViewport().setMinX(0);
                        graph.getViewport().setMaxX(keys.length);

                        graph.getViewport().setYAxisBoundsManual(true);
                        graph.getViewport().setMinY(0);
                        graph.getViewport().setMaxY(5);

                        graph.addSeries(series);


                        // Get the JSON array
                            /*Map<String,String> result = (Map<String, String>) response;

                            JSONArray array = response.getJSONArray("students");

                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);*/

                        // Get the current student (json object) data
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        mRequestQueue.add(jsonObjectRequest);


//        RecyclerView recyclerView = rootView.findViewById(R.id.products_list);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);

        return rootView;
    }

    private DataPoint[] generateData() {
        DataPoint[] values = new DataPoint[keys.length];
        for (int i = 0; i < keys.length; i++) {
            double x = i+1/*Double.parseDouble(keys[i])*/;
            int y = Integer.parseInt(objects.get(keys[i]));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }



}
