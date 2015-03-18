package alexpopa95.databasequery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import alexpopa95.databasequery.DB.Database;

/**
 * Created by alexpopa95 on 17/03/15.
 */
public class CreateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crea_tabella);
    }

    public void aggiungiTabella(View view) {
        TextView tb = (TextView) findViewById(R.id.editText);
        String text = tb.getText().toString();
        if(text != null) {
            Database.aggiungiTabella(tb.getText().toString());
            this.finish();
        }
        else {
            Toast.makeText(this, "Compila tutti i campi!", Toast.LENGTH_SHORT).show();
        }
    }
}
