package fr.estei.dbexemple;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import fr.estei.dbexemple.DbUtils.ProductContract;
import fr.estei.dbexemple.DbUtils.ProductDbHelper;

public class MainActivity extends AppCompatActivity {

    long lastInsertedProduct = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button productAdder  =  (Button)findViewById(R.id.button);
        productAdder.setOnClickListener(productAddListener);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    View.OnClickListener productAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int price = Integer.parseInt(getEditText2().getText().toString());
            String desc = getEditText3().getText().toString();
            String name  = getEditText().getText().toString();
            ProductDbHelper myDbHelper = new ProductDbHelper(getApplicationContext());
            SQLiteDatabase db = myDbHelper.getWritableDatabase();

            if(lastInsertedProduct != -1){
                ContentValues object = new ContentValues();
                // nouvelle valeur de la colonne nom
                object.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME,name);
                // recupération de l'objet en function de l'id => lastInsertedid (long)
                    String selection = ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_ID + "LIKE ?";
                    String[] selectionArgs = {String.valueOf(lastInsertedProduct)};
                int count  = db.update(
                        ProductContract.ProductEntry.TABLE_NAME, /** nom de la table*/
                        object, /*Content value avec les colonnes modifiées */
                        selection, /* selection du/des object a modifie (ici unique car basé sur l'id)*/
                        selectionArgs /*argument utilisé dans la seletion en remplacement du ? */);

            }else {
                ContentValues object = new ContentValues();
                object.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME,name);
                object.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_DESC,desc);
                object.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE,price);
                lastInsertedProduct = db.insert(ProductContract.ProductEntry.TABLE_NAME,null,object);
            }








        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private EditText getEditText(){
        return (EditText) findViewById(R.id.editText);
    }

    private EditText getEditText2(){
        return (EditText) findViewById(R.id.editText2);
    }

    private EditText getEditText3(){
        return (EditText) findViewById(R.id.editText3);
    }
}
