package com.smartcard.iotaspirantsvendor.provider;

import android.os.Build;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.smartcard.iotaspirantsvendor.BuildConfig;
import com.smartcard.iotaspirantsvendor.data.model.AbstractResponse;
import com.smartcard.iotaspirantsvendor.data.model.Product;

import java.io.UnsupportedEncodingException;


public class VolleyRequest<T> extends JsonRequest<T> {

    protected static final String TAG = VolleyRequest.class.getSimpleName();

    private final static Gson GSON = new Gson();

    private Class<T> responseClass;
    private static final String HOST_NAME = "https://5lexhjd5b7.execute-api.us-west-2.amazonaws.com/default/";
    private static final String SAVE =  HOST_NAME + "saveProducts";
    private static final String GET =  "https://bpvqofi2dh.execute-api.us-west-2.amazonaws.com/default/getItem" + "saveProducts";


    public VolleyRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public VolleyRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    public VolleyRequest(int httpMethod, String url, Response.Listener<T> listener,
                                  Response.ErrorListener errorListener) {
        this(httpMethod, url, null, listener, errorListener);
    }

    public VolleyRequest(int httpMethod, String url, Object requestBody, Response.Listener<T> listener,
                                  Response.ErrorListener errorListener) {
        super(httpMethod, url, GSON.toJson(requestBody), listener, errorListener);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Method:" + httpMethod + " URL=" + url);
        }
        setShouldCache(false); // do not cache any request.
    }

    public static VolleyRequest save(Response.Listener<AbstractResponse> listener, Product product,
                                     Response.ErrorListener errorListener) {
        Log.d(TAG, "Method:" + product.toString());
        return new VolleyRequest(Method.POST, SAVE, product, listener, errorListener)
                .setTag(Product.class.getSimpleName())
                .setClass(AbstractResponse.class);
    }


    public static VolleyRequest getProducts(Response.Listener<AbstractResponse> listener, Product product,
                                     Response.ErrorListener errorListener) {
        Log.d(TAG, "Method:" + product.toString());
        return new VolleyRequest(SAVE, listener, errorListener)
                .setTag(Product.class.getSimpleName())
                .setClass(AbstractResponse.class);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            T jsonResponse = GSON.fromJson(json, responseClass);
            return Response.success(
                    jsonResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


    public VolleyRequest<T> setTag(String tag) {
        super.setTag(tag);
        return this;
    }


    public VolleyRequest<T> setClass(Class<T> c) {
        responseClass = c;
        return this;
    }

    public void enqueue(RequestQueue requestQueue) {
        requestQueue.cancelAll(this.getTag());
        requestQueue.add(this);
    }
}