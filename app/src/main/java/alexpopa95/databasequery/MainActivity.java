package alexpopa95.databasequery;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import alexpopa95.databasequery.DB.Database;


public class MainActivity extends ActionBarActivity {

    String col1[];
    String col2[];
    int numero_righe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setAllEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.set_data_menu) {
            setData();
            return true;
        }
        if (id == R.id.connect_menu) {
            connect();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void creaTabella(View view) {
        Intent it = new Intent(this, CreateActivity.class);
        startActivity(it);
    }

    public void setData() {
        Intent it = new Intent(this, SetDataActivity.class);
        startActivity(it);
    }

    public void inserisciDati(View view) {
        Intent it = new Intent(this, InsertActivity.class);
        startActivity(it);
    }

    public void interrogaTabella(View view) {
        Intent it = new Intent(this, QuestionActivity.class);
        startActivity(it);
    }

    public void impostaTabella() {

        for(int i=0; i<numero_righe;i++) {

        }
    }

    public void connect() {
        if(Database.connect()) {
            //Connessione OK
            setAllEnabled(true);
        }
        else {
            //Connessione NON OK
            setAllEnabled(false);
        }
    }

    private void setAllEnabled(boolean cond) {
        Button b1 = (Button) findViewById(R.id.crea);
        Button b2 = (Button) findViewById(R.id.inserisci);
        Button b3 = (Button) findViewById(R.id.interroga);

        b1.setEnabled(cond);
        b2.setEnabled(cond);
        b3.setEnabled(cond);
    }
}
