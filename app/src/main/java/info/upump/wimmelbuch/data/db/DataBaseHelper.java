package info.upump.wimmelbuch.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import info.upump.wimmelbuch.R;

/**
 * Created by explo on 19.01.2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    public final static String DATABASE_NAME = "book.db";
    public final static String TABLE_BOOK = "book";
    public final static String TABLE_PAGE = "page";
    public final static int DATA_BASE_VERSION = 3;

    public static final String TABLE_KEY_ID = "_id";
    public static final String TABLE_KEY_BOOK_ID = "book_id";
    public static final String TABLE_KEY_TITLE = "title";
    public static final String TABLE_KEY_RATE = "rate";
    public static final String TABLE_KEY_IMG_TITLE = "img_title";
    public static final String TABLE_KEY_NUMBER_PAGE = "number_page";
    public static final String TABLE_KEY_IMG_PAGE = "img_page";

    public static  String DB_PATH;

    private static DataBaseHelper instance;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
        DB_PATH = context.getString((R.string.db_path)) + DATABASE_NAME;
    }

    public static final String CREATE_TABLE_BOOK =
            "CREATE TABLE " + TABLE_BOOK +
                    "(" +
                    TABLE_KEY_ID + " integer NOT NULL primary key autoincrement, " +
                    TABLE_KEY_TITLE + " text, " +
                    TABLE_KEY_RATE + " integer, " +
                    TABLE_KEY_IMG_TITLE + " text)";


    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL(CREATE_TABLE_BOOK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }
    public void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;

        if (checkBD()) {
            return;
        }

        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                //получаем локальную бд как поток в папке assets
                System.out.println("сменить базу");
                this.getReadableDatabase();

                myInput = context.getAssets().open(DATABASE_NAME);

                // Путь к новой бд
                String outFileName = DB_PATH;
                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;

                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
                close();

            }
            seVersionDB();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void deleteBD(){
        System.out.println("удалем старую");
        File file = new File(DB_PATH);
        if(file.exists()){
            file.delete();
        }
    }

    private void seVersionDB() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.setVersion(DATA_BASE_VERSION);
            sqLiteDatabase.close();
        }catch (SQLiteException e) {

        }
    }

    private boolean checkBD() {
        SQLiteDatabase sqLiteDatabase;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH,null,  SQLiteDatabase.OPEN_READONLY);
            int version = sqLiteDatabase.getVersion();
            System.out.println("версия базы "+version);
            sqLiteDatabase.close();
            if(version < DATA_BASE_VERSION){
                deleteBD();
                return false;
            } else return true;
        } catch (SQLiteException e) {
            return false;
        }
    }
}
