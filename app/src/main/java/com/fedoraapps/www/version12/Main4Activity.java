package com.fedoraapps.www.version12;

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

import static android.R.layout.simple_list_item_1;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {


    ListView Listaencomiendas;
    SearchView sv;
    GETENCOMIENDAS hiloconexion;
    static int codViaje;
    //    String IP = "https://lacbus.firebaseio.com";
    JSONArray respuestaJSON;
    // String GET = "https://lacbus.firebaseio.com/AppEncomiendasAndroid/viajes.json?orderBy=\"destinoId\"&equalTo=";
    String GET = "https://lacbus.firebaseio.com/AppEncomiendasAndroid/encomiendas.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        codViaje = getIntent().getExtras().getInt("codigo");
        Listaencomiendas = (ListView)findViewById(R.id.lista);
        Listaencomiendas.setTextFilterEnabled(true);

        sv = (SearchView)findViewById(R.id.searchView1);
        sv.setOnClickListener(this);

        hiloconexion = new GETENCOMIENDAS();
        hiloconexion.execute(GET);

        final ArrayAdapter<encomienda> adaptador = new ArrayAdapter<encomienda>(this, simple_list_item_1,Farcade.listaEncomiendas);
        Listaencomiendas.setAdapter(adaptador);
        Listaencomiendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //String tex = listado.getItemAtPosition(position).toString();
               // viaje v = adaptador.getItem(position);
               // int cod = v.getCodV();
                //texto.setText(cod);
                // Intent i = new Intent(Main3Activity.this, Main3Activity.class);
                //i.putExtra("codigo", cod);
                //startActivity(i);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    private class GETENCOMIENDAS extends AsyncTask<String, Void, JSONArray> {


        @Override
        protected void onPostExecute(JSONArray s) {

            for (int i = 0; i < s.length(); i++) {
                try {
                    JSONObject json = s.getJSONObject(i);
                    int codBarrax = s.getJSONObject(i).getInt("codigoBarra");
                    int codDestino = s.getJSONObject(i).getInt("destinoId");
                    String emisor = s.getJSONObject(i).getString("emisor").toString();
                    int cedEmisor = s.getJSONObject(i).getInt("emisorCI");
                    int codEncomienda = s.getJSONObject(i).getInt("id");
                    int origenID = s.getJSONObject(i).getInt("origenId");
                    String receptor = s.getJSONObject(i).getString("receptor").toString();
                    int cedReceptor = s.getJSONObject(i).getInt("receptorCI");
                    int codV = s.getJSONObject(i).getInt("viajeId");
                    encomienda Encomienda = new encomienda(codBarrax,codDestino,emisor,cedEmisor,codEncomienda,origenID,receptor,cedReceptor,codV);

                    if(Farcade.getEncomiendasPorCodigo(codViaje,Encomienda.getCodViaje())==true) {
                        Farcade.listaEncomiendas.add(Encomienda);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


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
                    //System.out.println(result.toString());
                    //Creo un JsonArray para leer los campos
                    respuestaJSON = new JSONArray(result.toString());

                    //JSONObject nombre = respuestaJSON.getJSONObject(0);


                    System.out.println(respuestaJSON.toString());
                    //String resultJSON = respuestaJSON.getString("estado");//estado es el nombre del campo del JSON
                    //System.out.println(result.toString());
                    //JSON = new JSONArray(respuestaJSON.get("0"));
                    //String nombre = (String) andresputo.get("nombre");

                }

                return respuestaJSON;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
