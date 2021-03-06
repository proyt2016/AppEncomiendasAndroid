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

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    ListView listadoViajes;
    SearchView sv;
    GetViajes hiloconexion;
   static int codTerminal;
//    String IP = "https://lacbus.firebaseio.com";
    JSONArray respuestaJSON;
       // String GET = "https://lacbus.firebaseio.com/AppEncomiendasAndroid/viajes.json?orderBy=\"destinoId\"&equalTo=";
    String GET = "https://lacbus.firebaseio.com/AppEncomiendasAndroid/viajes.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
          codTerminal = getIntent().getExtras().getInt("codigo");


        listadoViajes = (ListView)findViewById(R.id.lista);
        listadoViajes.setTextFilterEnabled(true);

        sv = (SearchView)findViewById(R.id.searchView1);
        sv.setOnClickListener(this);

        hiloconexion = new GetViajes();
        hiloconexion.execute(GET);

        final ArrayAdapter<viaje> adaptador = new ArrayAdapter<viaje>(this, simple_list_item_1,Farcade.listaViajes);
        listadoViajes.setAdapter(adaptador);
        listadoViajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //String tex = listado.getItemAtPosition(position).toString();
                viaje v = adaptador.getItem(position);
                int cod = v.getCodV();
                //texto.setText(cod);
                Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                i.putExtra("codigo", cod);
                startActivity(i);
            }
        });



    }

    @Override
    public void onClick(View v) {

    }

    private class GetViajes extends AsyncTask<String,Void,JSONArray> {


        @Override
        protected void onPostExecute(JSONArray s) {
            List<terminal> listaTerminalViaje = new ArrayList<>();
           List<encomienda> ListaEncomienda = new ArrayList<>();
            for(int i=0; i< s.length();i++){
                try {
                    JSONObject json = s.getJSONObject(i);
                    int codOrigen = s.getJSONObject(i).getInt("origenId");
                    int codDestino = s.getJSONObject(i).getInt("destinoId");
                    String name = s.getJSONObject(i).getString("nombre").toString();
                    int codV = s.getJSONObject(i).getInt("id");
                    String fecha = s.getJSONObject(i).getString("fecha").toString();
                    String hraArrivo = s.getJSONObject(i).getString("llegada").toString();
                    String hraSalida = s.getJSONObject(i).getString("salida").toString();
                    int nroCoche = s.getJSONObject(i).getInt("nroCoche");
                    viaje Viaje = new viaje(codDestino,codOrigen,name,codV,fecha,hraArrivo,hraSalida,nroCoche,listaTerminalViaje,ListaEncomienda);

                  if (Farcade.getViajesPorCodigo(codTerminal,Viaje.getCodV())== true){
                    Farcade.listaViajes.add(Viaje);}
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
            return  null;
        }









    }


}
