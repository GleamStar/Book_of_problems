package artinbyte.alex.BookOfproblems;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alexey on 29.07.2015.
 */
class DatabaseOpen extends SQLiteOpenHelper {

    public DatabaseOpen(Context context, String name,
                                       SQLiteDatabase.CursorFactory factory, int version)
    {
        super (context, name, factory, version);
     }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.v("CREATE", "CREATE");
        String createQuery = "CREATE TABLE users" +
                "(Name  text primary key," +
                "pass TEXT);" ;
        String task = "CREATE TABLE task" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "Name TEXT,"+
                "nameproblem TEXT,description TEXT," +
                "date TEXT," + "check_ INTEGER,"+
                "FOREIGN KEY(Name) REFERENCES users(Name)" +
                 ");";



        db.execSQL(task); // Выполнение запроса для создания базы данных
    db.execSQL(createQuery);
    }
    @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
           {
           }

}
