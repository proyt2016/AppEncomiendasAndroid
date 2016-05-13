package com.fedoraapps.www.version12;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by maxi on 13/05/2016.
 */
public abstract class GetViajes extends AsyncTask<String,Void,JSONArray> {
        protected abstract void onPostExecute(JSONObject result);

        @Override
        protected abstract JSONArray doInBackground(String... params);
}
