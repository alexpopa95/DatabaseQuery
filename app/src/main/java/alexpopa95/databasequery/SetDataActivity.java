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
public class SetDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_data);
        TextView tv3 = (TextView) findViewById(R.id.editText8);
        TextView tv4 = (TextView) findViewById(R.id.editText9);
        TextView tv5 = (TextView) findViewById(R.id.editText10);

        tv3.setText(Database.username);
        tv4.setText(Database.password);
        tv5.setText(Database.db_name);


    }

    public void setDataAction(View view) {
        TextView tv3 = (TextView) findViewById(R.id.editText8);
        TextView tv4 = (TextView) findViewById(R.id.editText9);
        TextView tv5 = (TextView) findViewById(R.id.editText10);

        Database.impostaDati(tv3.getText().toString(), tv4.getText().toString(), tv5.getText().toString(), this);

        //MainActivity.main.connect();

        this.finish();
    }
}