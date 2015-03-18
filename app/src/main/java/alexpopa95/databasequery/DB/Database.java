package alexpopa95.databasequery.DB;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpopa95 on 17/03/15.
 */
public class Database {

    static String crea_tabella_url = "http://www.alexpopa.it/DatabaseQuery/crea_tabella.php";
    static String connect_url = "http://www.alexpopa.it/DatabaseQuery/connect.php";
    static String get_tabelle_url = "http://www.alexpopa.it/DatabaseQuery/get_tabelle.php";
    static String aggiungi_dati_url = "http://www.alexpopa.it/DatabaseQuery/aggiungi_dati.php";
    static String get_dati_url = "http://www.alexpopa.it/DatabaseQuery/get_dati.php";

    static Context context;
    static Activity activity;
    //Dati
    static boolean ok;
    public static String username = "u394472257_test";
    public static String password = "";
    public static String db_name = "u394472257_test";

    static InputStream is=null;
    static String result=null;
    static String line=null;
    static int code;
    static int numero_righe;

    public static void setContext(Activity act) {
        Database.activity = act;
        Database.context = act.getApplicationContext();
    }

    public static boolean connect() {
        if(username==null || password==null || db_name==null) return false;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("db_name", db_name));

        Log.e("DB_USERNAME", username);
        Log.e("TABLE_NAME", db_name);
        Log.e("DB_PASSWORD", password);

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(connect_url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(context, "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            Log.e("RESULT_API", result);
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                ok = true;
                Toast.makeText(context, "Connesso con successo",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            else
            {
                ok = false;
                Toast.makeText(context, "Errore. Conessione riuscita",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
            Toast.makeText(context, "ERRORE! Controlla i dati di accesso...",
                Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static void impostaDati(String username, String password, String db_name, Activity cont) {
        Database.username = username;
        Database.password = password;
        Database.db_name = db_name;

        setContext(cont);

        Toast.makeText(context, "Dati impostati",
                Toast.LENGTH_SHORT).show();
    }

    public static void aggiungiTabella(String nomeTabella) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("nome", nomeTabella));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("db_name", db_name));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(crea_tabella_url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(context, "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            Log.e("RESULT_API", result);
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(context, "Tabella "+nomeTabella+" inserita con successo",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Errore. Inserimento non riuscito",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public static void aggiungiDati(String nomeTabella, String info1, String info2) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("nome", nomeTabella));
        nameValuePairs.add(new BasicNameValuePair("info1", info1));
        nameValuePairs.add(new BasicNameValuePair("info2", info2));

        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("db_name", db_name));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(aggiungi_dati_url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(context, "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            Log.e("RESULT_API", result);
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(context, "Dati inseriti con successo in "+nomeTabella,
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Errore. Inserimento non riuscito",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public static List[] riceviDati(String nomeTabella) {
        List col1 = new ArrayList<String>();
        List col2 = new ArrayList<String>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("nome", nomeTabella));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("db_name", db_name));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(get_dati_url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(context, "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            Log.e("RESULT_API", result);
            JSONObject json_result = new JSONObject(result);
            code=(json_result.getInt("code"));

            if(code==1)
            {
                numero_righe = (json_result.getInt("num"));
                Log.e("NUMERO_RIGHE", ""+numero_righe);
                for(int i=0; i<numero_righe; i++) {
                    col1.add(json_result.getString("col1row" + i));
                    col2.add(json_result.getString("col2row" + i));
                }

                Toast.makeText(context, "Dati inseriti",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, "Errore. Lettura non riuscita",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }

        List contenuto[] = {col1, col2};
        return contenuto;
    }

    public static List getNomiTabelle() {

        List tab = new ArrayList<String>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("db_name", db_name));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(get_tabelle_url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(context, "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            Log.e("RESULT_API", result);
            JSONObject json_result = new JSONObject(result);
            code=(json_result.getInt("code"));

            if(code==1)
            {
                numero_righe = (json_result.getInt("num"));
                Log.e("NUMERO_RIGHE", ""+numero_righe);
                for(int i=0; i<numero_righe; i++) {
                    tab.add(json_result.getString("tab" + i));
                }
            }
            else
            {
                Toast.makeText(context, "Errore. Lettura non riuscita",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }

        return tab;
    }
}
