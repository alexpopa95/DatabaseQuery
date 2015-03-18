package alexpopa95.databasequery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import alexpopa95.databasequery.DB.Database;

/**
 * Created by alexpopa95 on 17/03/15.
 */
public class QuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interroga_tabella);

        Spinner sp = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Database.getNomiTabelle());
        sp.setAdapter(adapter);
    }

    public void inserisciDatiTabella(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        List data[];

        data = Database.riceviDati(spinner.getSelectedItem().toString());

        MainActivity.main.setTableContent(data);

        this.finish();
    }
}
