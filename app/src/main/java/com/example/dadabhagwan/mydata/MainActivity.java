package com.example.dadabhagwan.mydata;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText name;
    EditText password,enterData;
    Button add;
    create_db_adapter connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= (EditText) findViewById(R.id.tvName);
        password= (EditText) findViewById(R.id.tvpassword);
        add= (Button) findViewById(R.id.btnInsert);
        enterData= (EditText) findViewById(R.id.et_data);
        connection=new create_db_adapter(this);




        // SQLiteDatabase sqLiteDatabase=connection.getWritableDatabase();
         }
    public void addData(View v)
    {
       String Name=name.getText().toString();
       String Password=password.getText().toString();
       long id= connection.inserData(Name,Password);
        if(id<0)
        {
            Toast.makeText(this, "Data Unsuccessful.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data Inserted..", Toast.LENGTH_SHORT).show();
        }
    }
    public void DisplayData(View v)
    {
        String full_data=enterData.getText().toString();
        String str1=full_data.substring(0,full_data.indexOf(" "));
        String str2=full_data.substring(full_data.indexOf(" ")+1);
        String data=connection.selectId(str1,str2);
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
    public void Update(View v)
    {
       connection.UpdateData("HARDIKA","HARDIK");
    }
    public void showAll(View v)
    {
        String full=connection.getAllData();
        Toast.makeText(this, full, Toast.LENGTH_SHORT).show();
    }
    public void Deleting(View v)
    {
       int counter= connection.Delete();
        Toast.makeText(this, " "+counter, Toast.LENGTH_SHORT).show();
    }

}
