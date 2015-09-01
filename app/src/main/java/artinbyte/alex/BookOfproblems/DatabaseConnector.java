package artinbyte.alex.BookOfproblems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseConnector {

   public static final String DATABASE_NAME = "BKPB" ;
    public static final String NAME_USERS_COLUM = "Name";
    public static final String PASS_USERS_COLUM = "pass";
    public static final String NAME_TASK = "nameproblem";
    public static final String DESCRIPTION_TASK = "description";
    public static final String DATE_TASK = "date";
    private static final String CHECK = "check_";


    private SQLiteDatabase database ;
    private  DatabaseOpen databaseOpenHelper ;


    public   DatabaseConnector (Context context)
    {

           databaseOpenHelper =
                    new DatabaseOpen(context, DATABASE_NAME, null, 1);

    }
    public  void open() throws SQLException
    {
        database = databaseOpenHelper.getWritableDatabase();
    }
    public  void close()
    {
        if (database != null)
            database.close();
    }
    public  long insertUser (String Name, String Pass)
    {
        ContentValues newUser = new ContentValues();
        newUser.put(NAME_USERS_COLUM, Name);
        newUser.put(PASS_USERS_COLUM, Pass);
        open();
        long rowID = database.insert("users",null,newUser);
        close();

        return rowID;
    }
     public  long insertTsk (String Name ,String nameproblam ,String Description ,String Date,Integer CK)
     {
         open();

         ContentValues newTask = new ContentValues();
         newTask.put(NAME_USERS_COLUM,Name);
         newTask.put(NAME_TASK, nameproblam);
         newTask.put(DESCRIPTION_TASK, Description);
         newTask.put(DATE_TASK, Date);
         newTask.put(CHECK, CK);
         long rowID = database.insert("task",null,newTask);

         Log.v("EXeption", "insert");
         close();
         return rowID;

     }
    public  void updateUser (String Name, String Pass)
    {
        ContentValues editUser = new ContentValues();
        editUser.put(NAME_USERS_COLUM, Name);
        editUser.put(PASS_USERS_COLUM, Pass);
        open();
        database.update("users", editUser, null, null);
        close();
    }
    public  void updateTask (String description ,Integer check)
    {
        open();
        String update;
        ContentValues editTask = new ContentValues();
        if (check == 1) {
            editTask.put(CHECK, 1);
            update ="1";
        }

            else {
            editTask.put(CHECK, 0);
            update = "0";
        }



        database.execSQL("UPDATE task SET check_= " + update +" WHERE description= '" + description +"';");
        close();
    }
    public Boolean Authorization(String NameUser, String password) {

        open();
        String query = "SELECT * FROM users ";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        Boolean result = false;
        if (cursor != null) {

            if (cursor.moveToFirst()) {

                do {

                    String Name = cursor.getString(cursor.getColumnIndex(NAME_USERS_COLUM));
                    String Pass = cursor.getString(cursor.getColumnIndex(PASS_USERS_COLUM));
                    if (Name.equals(NameUser) && Pass.equals(password)) {
                        result = true;
                        break;
                    }
                } while (cursor.moveToNext());

            }

        }
        return result ;
    }
    public void  deletetask (int id)
    {
        open(); // open the database
            database.delete("task", "_id=" + id, null);
             close(); // close

    }
    public ArrayList<ToDoItem> onCursorClick(String namespace) {
        Log.v("EXeption", "START");
        ArrayList<ToDoItem> list = new ArrayList<>();

        open();

            String query = "SELECT * FROM task WHERE " + NAME_USERS_COLUM + " = '" + namespace +"'";

            Cursor cursor = database.rawQuery(query, null);
            Integer index;
            String name;
            String description;
            String date;
        cursor.moveToFirst();
            if (cursor != null) {

                if (cursor.moveToFirst()) {

                    do {

                        index = cursor.getInt(cursor
                                .getColumnIndex("_id"));
                        name = cursor.getString(cursor
                                .getColumnIndex(NAME_TASK));
                        description = cursor.getString(cursor
                                .getColumnIndex(DESCRIPTION_TASK));
                        date = cursor.getString(cursor
                                .getColumnIndex(DATE_TASK));
                        String fuck = cursor.getString(cursor
                                .getColumnIndex(NAME_USERS_COLUM));
                        Boolean check  = (cursor.getInt(cursor
                                .getColumnIndex(CHECK))==1)?true:false;

                        list.add(new ToDoItem(name, description, date, index,check));

                    } while (cursor.moveToNext());

                }

            }

            cursor.close();

                  close();
                return list;

    }



}
