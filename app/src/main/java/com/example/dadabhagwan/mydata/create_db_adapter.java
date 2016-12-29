package com.example.dadabhagwan.mydata;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;

import static android.content.ContentValues.TAG;

/**
 * Created by dadabhagwan on 10/15/2016.
 */

public class create_db_adapter {

    create_db ob;
    create_db_adapter(Context context)
    {
        ob=new create_db(context);

    }
    public long inserData(String name,String password)
    {
       SQLiteDatabase sqli= ob.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(create_db.NAME,name);
        content.put(create_db.PASSWORD1,password);
        long id=sqli.insert(create_db.TABLE_NAME,null,content);
        return id;


    }
    public String getAllData()
    {
     SQLiteDatabase SQL1=ob.getWritableDatabase();
        String[] columns={create_db.UID,create_db.NAME,create_db.PASSWORD1};
        Cursor cursor=SQL1.query(create_db.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            int id=cursor.getColumnIndex(create_db.UID);
            String id1=cursor.getString(id);
            int name=  cursor.getColumnIndex(create_db.NAME);
            String name1=   cursor.getString(name);
            int password=cursor.getColumnIndex(create_db.PASSWORD1);
            String password1=cursor.getString(password);
            buffer.append(id1+" "+name1+" "+password1+"\n ");
        }
        return buffer.toString();
    }
    public String selectId(String name1,String password1)
    {
        SQLiteDatabase SQL2=ob.getWritableDatabase();
        String[] columns={create_db.UID};
        String[] select_argd={name1,password1};
        Cursor cursor=SQL2.query(create_db.TABLE_NAME,columns,create_db.NAME+" =? AND " + create_db.PASSWORD1 + "=?",select_argd,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            int id=cursor.getColumnIndex(create_db.UID);
            String id1=cursor.getString(id);
            buffer.append(id1+ "\n");
        }
       return buffer.toString();
    }
    public int UpdateData(String newVal,String oldVal)
    {
        SQLiteDatabase SQL3=ob.getWritableDatabase();
        ContentValues newValue=new ContentValues();
        newValue.put(create_db.NAME,newVal);
        String[] oldValue={oldVal};
        int count=SQL3.update(create_db.TABLE_NAME,newValue,create_db.NAME+" =? ",oldValue);
        return count;

    }
    public int Delete()
    {
        String[] delete_name={""};
        SQLiteDatabase SQL4=ob.getWritableDatabase();
        int count1=SQL4.delete(create_db.TABLE_NAME,create_db.NAME+" =? ",delete_name);
        return count1;

    }

    class create_db extends SQLiteOpenHelper  {
        private static final String DATABASE_NAME="TEST";
        private static final String TABLE_NAME="LOGIN";
        private static final int DATABASE_VERSION=16;
        private static final String NAME="NAME";
        private static final String UID="_ID";
        private static final String PASSWORD1="PASSWORD";
        private static final String query="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PASSWORD1+" VARCHAR(255));";
        private static final String query1="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        public create_db(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;

            Toast.makeText(context, "constructor is called..", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                sqLiteDatabase.execSQL(query);
                Toast.makeText(context, "Table Created successfully..", Toast.LENGTH_SHORT).show();
            }
            catch (android.database.SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            try {

                sqLiteDatabase.execSQL(query1);
                Toast.makeText(context, "Table deleted Successfully..", Toast.LENGTH_SHORT).show();
                onCreate(sqLiteDatabase);


            }
            catch (android.database.SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

}
