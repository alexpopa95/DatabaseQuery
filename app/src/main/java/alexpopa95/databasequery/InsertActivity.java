package alexpopa95.databasequery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import alexpopa95.databasequery.DB.Database;

/**
 * Created by alexpopa95 on 17/03/15.
 */
public class InsertActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserisci_tabella);

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Database.getNomiTabelle());
        sp.setAdapter(adapter);
    }

    public void inserisciDatiTabella(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        TextView tb1 = (TextView) findViewById(R.id.editText4);
        TextView tb2 = (TextView) findViewById(R.id.editText5);
        String text1 = tb1.getText().toString();
        String text2 = tb2.getText().toString();
        if(text1 != null && text2 != null) {
            Database.aggiungiDati(spinner.getSelectedItem().toString(), tb1.getText().toString(), tb2.getText().toString());
            this.finish();
        }
        else {
            Toast.makeText(this, "Compila tutti i campi!", Toast.LENGTH_SHORT).show();
        }

    }
}
