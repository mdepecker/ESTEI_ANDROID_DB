package fr.estei.dbexemple.DbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mdepe on 14/10/2015.
 */
public class ProductDbHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERS = 1;
    public static String DATABASE_NAME = "product.db";


    private static final String CREATE_REQ =
            "CREATE TABLE "+ ProductContract.ProductEntry.TABLE_NAME +" IF NOT EXISTS (" +
                    ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_ID +" INTEGER PRIMARY KEY" +
                    ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME+" TEXT , " +
                    ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE +"INTEGER, " +
                    ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE+" TEXT)";

    private static final String DROP_REQ =
        "DROP TABLE IF EXISTS" + ProductContract.ProductEntry.TABLE_NAME;

    public ProductDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERS);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REQ);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_REQ);
        onCreate(db);
    }

}
