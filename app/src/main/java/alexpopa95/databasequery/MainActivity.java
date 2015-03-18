package alexpopa95.databasequery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alexpopa95.databasequery.DB.Database;


public class MainActivity extends ActionBarActivity {

    String col1[];
    String col2[];
    int numero_righe;

    private ViewGroup.LayoutParams layoutParams = null;

    static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        setAllEnabled(false);

        List c1 = new ArrayList<String>();
        c1.add("vuoto");
        List c2 = new ArrayList<String>();
        c2.add("vuoto");
        List data[] = {c1, c2};
        this.setTableContent(data);

        Database.setContext(this);
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

    public void setTableContent(List data[]) {
        TableRow row;
        TextView cell1 = null;
        TextView cell2 = null;
        List col1 = data[0];
        List col2 = data[1];

        TextView cl = (TextView) findViewById(R.id.col1);
        if(layoutParams == null) layoutParams = cl.getLayoutParams();
        TableLayout table = (TableLayout) findViewById(R.id.table);

        row = new TableRow(this);
        cell1 = new TextView(this);
        cell2 = new TextView(this);

        cell1.setLayoutParams(layoutParams);
        cell2.setLayoutParams(layoutParams);

        cell1.setTextSize(22);
        cell2.setTextSize(22);

        cell1.setTypeface(null, Typeface.BOLD);
        cell2.setTypeface(null, Typeface.BOLD);

        cell1.setText("Colonna 1");
        cell2.setText("Colonna 2");

        row.addView(cell1);
        row.addView(cell2);

        table.removeAllViews();
        table.addView(row);

        if(col1 != null && col2 !=null && (col1.size() == col2.size())) {
            for(int i=0; i<col1.size();i++) {
                cell1 = new TextView(this);
                cell2 = new TextView(this);
                row = new TableRow(this);

                cell1.setLayoutParams(layoutParams);
                cell2.setLayoutParams(layoutParams);

                cell1.setText(col1.get(i).toString());
                cell2.setText(col2.get(i).toString());

                cell1.setTextSize(18);
                cell2.setTextSize(18);

                row.addView(cell1);
                row.addView(cell2);

                table.addView(row);
            }
        }
    }
}
