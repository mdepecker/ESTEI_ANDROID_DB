package fr.estei.dbexemple.DbUtils;

import android.provider.BaseColumns;

public final class ProductContract {

    public ProductContract(){}

    public static class ProductEntry implements BaseColumns{
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_PRODUCT_ID = "product_id";
        public static final String COLUMN_NAME_PRODUCT_NAME = "product_name";
        public static final String COLUMN_NAME_PRODUCT_PRICE = "product_price";
        public static final String COLUMN_NAME_PRODUCT_DESC = "product_desc";
    }
}
