package info.upump.wimmelbuch.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by explo on 19.01.2018.
 */

abstract class DataBaseDao {
    protected SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;
    protected Context context;

    public DataBaseDao(Context context) {
        this.context = context;
        dataBaseHelper = DataBaseHelper.getHelper(context);
        open();
    }

    public void open() {
        if (dataBaseHelper == null)
            dataBaseHelper = DataBaseHelper.getHelper(context);
        database = dataBaseHelper.getWritableDatabase();

    }

    public void close() {
        dataBaseHelper.close();
        database = null;
    }

}
