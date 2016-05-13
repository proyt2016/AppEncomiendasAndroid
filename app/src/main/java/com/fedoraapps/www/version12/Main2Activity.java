package com.fedoraapps.www.version12;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    ListView listaTerminales;
    SearchView sv;
    JSONArray respuestaJSON;
    String IP = "https://lacbus.firebaseio.com";
    GetEncomiendas hiloconexion;
    //RUTAS DE LOS WS
    // String GET  = IP + "/AppGestion/usuarios.json?orderBy=\"username\"&equalTo=\"admin\""; validar con los campos correspondientes login
    String GET  = IP + "/AppEncomiendasAndroid/terminales.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaTerminales = (ListView)findViewById(R.id.lista);
        listaTerminales.setTextFilterEnabled(true);

        sv = (SearchView)findViewById(R.id.searchView1);
        sv.setOnClickListener(this);

       hiloconexion = new GetEncomiendas();
       hiloconexion.execute(GET);

        final ArrayAdapter<terminal> adaptador = new ArrayAdapter<terminal>(this, simple_list_item_1,Farcade.listaTerminal);
        listaTerminales.setAdapter(adaptador);
        listaTerminales.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //String tex = listado.getItemAtPosition(position).toString();
                terminal ter = adaptador.getItem(position);
                int cod = ter.getId();
                //texto.setText(cod);
                Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                i.putExtra("codigo", cod);
                startActivity(i);
            }
        });

  }

    @Override
    public void onClick(View v) {
     /* switch (v.getId()) {
           case R.id.searchView1:
                //hiloconexion = new GetEncomiendas();
                //hiloconexion.execute(GET);
               // final ArrayAdapter<terminal> adaptador = new ArrayAdapter<terminal>(this, simple_list_item_1,Farcade.listaTerminal);
                //listaTerminales.setAdapter(adaptador);
                break;
       }*/
    }

    public class GetEncomiendas extends AsyncTask<String,Void,JSONArray> {

        private JSONObject JSON;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            String cadena = params[0];
            String devuelve = "";

            URL url = null; //URL de donde queremos obtener la inforacion

            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();//Abrimos la conexion
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        "(Linux; Android 1.5; )");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(connection.getInputStream());//Preparo la cadena de entrada.
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    //Creo un JsonArray para leer los campos
                    respuestaJSON = new JSONArray(result.toString());
                    //String resultJSON = respuestaJSON.getString("estado");//estado es el nombre del campo del JSON
                    //JSON = new JSONObject(respuestaJSON.getJSONObject(0).toString());
                    //String nombre = (String) andresputo.get("nombre");

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return respuestaJSON;
        }
        @Override
        protected void onPostExecute(JSONArray s) {
            try {
                for(int i=0; i<s.length();i++){
                    JSONObject json = s.getJSONObject(i);
                    List<viaje> listaViajes = new ArrayList<viaje>();
                    int id = json.getInt("id");
                    String nom = json.getString("nombre").toString();
                    terminal t = new terminal(id,nom,listaViajes);
                    Farcade.listaTerminal.add(t);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //@Override
        protected void onCancelled(String s) {
            //super.onCancelled(s);
        }

    }


}
