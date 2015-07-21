package com.escom.comportamientonormal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ComportamientoNormal extends Activity {

    ListView lvData;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12;
    View v_1, v_2, v_3, v_4, v_5, v_6, v_7, v_8, v_9, v_10;
    String text, text2;
    int aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comportamiento_normal);
        lvData = (ListView) findViewById(R.id.ll_data);
        //requestQuery();
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        tv5 = (TextView) findViewById(R.id.tv_5);
        tv6 = (TextView) findViewById(R.id.tv_6);
        tv7 = (TextView) findViewById(R.id.tv_7);
        tv8 = (TextView) findViewById(R.id.tv_8);
        tv9 = (TextView) findViewById(R.id.tv_9);
        tv10 = (TextView) findViewById(R.id.tv_10);
        tv11 = (TextView) findViewById(R.id.tv_11);
        tv12 = (TextView) findViewById(R.id.tv_med);

        v_1 =  findViewById(R.id.v_1);
        v_2 =  findViewById(R.id.v_2);
        v_3 =  findViewById(R.id.v_3);
        v_4 =  findViewById(R.id.v_4);
        v_5 =  findViewById(R.id.v_5);
        v_6 =  findViewById(R.id.v_6);
        v_7 =  findViewById(R.id.v_7);
        v_8 =  findViewById(R.id.v_8);
        v_9 =  findViewById(R.id.v_9);
        v_10 =  findViewById(R.id.v_10);

        obtieneJSON();


    }

    public void requestQuery(){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        String url = "http://appvisiondesigner.com/estadistica/conexion.php";
        RequestParams params = new RequestParams();
        params.put("valor", 1);

        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //cargarLista(obtieneJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<String> obtieneJSON (){

        ArrayList<String> listado = new ArrayList<String>();
        ArrayList<Integer> numbers = new ArrayList<Integer>();


        InputStream inputStream = getResources().openRawResource(R.raw.estadistica);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("FUCK", byteArrayOutputStream.toString());

        //int valor2 = 1;
        try {
            JSONObject jsonObject = new JSONObject(byteArrayOutputStream.toString());
            JSONObject jObjectResult = jsonObject.getJSONObject("values");
            JSONArray jArray = jObjectResult.getJSONArray("value");

            Log.e("FUCK", jArray.toString());
            String text;
            int intItem;
            for (int i = 0; i<jArray.length(); i++ ){
                text = jArray.getJSONObject(i).getString("media");
                intItem = Integer.parseInt(text);
                listado.add(text);
                numbers.add(intItem);
                //valor2 = valor2 + Integer.parseInt(text);
                //tv12.setText(valor2/100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.e("FUCK", numbers.get(0) + " min " + numbers.get(99)+"" );

        int valor = numbers.get(99)/100;
        for(int j = 0; j<11; j++) {
            if (j == 0) {tv1.setText(numbers.get(0).toString());}
            else if (j == 1) {
                tv2.setText(String.valueOf(valor));
                for (int k = 0; numbers.get(k) < valor; k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_1.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = k*2;
                    v_1.setLayoutParams(params);
                    aux = k;
                }
                Log.e("FUCK VALOR FINAL AUX", aux + "");
            }
            else if (j == 2) {
                tv3.setText(String.valueOf(valor * 2));
                Log.e("FUCK VALOR NUMERO 1 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux) < (valor * 2); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_2.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_2.setLayoutParams(params); //causes layout update
                    aux = k;
                    Log.e("FUCK VALOR", aux + "");
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                }
            }
            else if (j == 3) {
                tv4.setText(String.valueOf(valor * 3));
                Log.e("FUCK VALOR NUMERO 3 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux) < (valor * 3); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_3.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_3.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            }
            else if (j == 4) {
                tv5.setText(String.valueOf(valor * 4));
                Log.e("FUCK VALOR NUMERO 4 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux) < (valor * 4); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_4.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_4.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 5) {
                tv6.setText(String.valueOf(valor * 5));
                Log.e("FUCK VALOR NUMERO 5 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux) < (valor * 5); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_5.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_5.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 6) {
                tv7.setText(String.valueOf(valor * 6));
                Log.e("FUCK VALOR NUMERO 6 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux ) < (valor * 6); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_6.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_6.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 7) {
                tv8.setText(String.valueOf(valor * 7));
                Log.e("FUCK VALOR NUMERO 7 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux ) < (valor * 7); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_7.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_7.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 8) {
                tv9.setText(String.valueOf(valor * 8));
                Log.e("FUCK VALOR NUMERO 8 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux ) < (valor * 8); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_8.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_8.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 9) {
                tv10.setText(String.valueOf(valor * 9));
                Log.e("FUCK VALOR NUMERO 9 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux ) < (valor * 9); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_9.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_9.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            } else if (j == 10) {
                tv11.setText(String.valueOf(valor * 10));
                //Log.e("FUCK VALOR NUMERO 10 aux", aux + "");
                int count = 1;
                for (int k = aux; numbers.get(aux ) < (valor * 10); k++){
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v_10.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.height = count*2;
                    v_10.setLayoutParams(params); //causes layout update
                    aux = k;
                    count++;
                    Log.e("FUCK VALOR aux", aux + "");
                    Log.e("FUCK VALOR Count ", count + "");

                }
            }
        }

        return listado;
    }

    public void cargarLista(ArrayList<String> listado){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
        lvData.setAdapter(adapter);
    }

}
