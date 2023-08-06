package com.example.safety;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Registor extends AppCompatActivity { Button b1,b2,b3;
    EditText e1;

    ListView listView;
    SQLiteOpenHelper s1;
    SQLiteDatabase sqlite;
    DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_registor);
        e1=findViewById(R.id.phone);
        b1=findViewById(R.id.add);
        b2=findViewById(R.id.delete);
        b3=findViewById(R.id.view);

        myDB=new DatabaseHandler(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                String sr=e1.getText().toString();
                addData(sr);
            Toast.makeText(Registor.this,"Data added",Toast.LENGTH_SHORT).show();
            e1.setText( "" );
        }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                sqlite =myDB.getWritableDatabase();
                String x=e1.getText().toString(); DeleteData(x);
            Toast.makeText(Registor.this,"Data Deleted",Toast.LENGTH_SHORT).show();
        }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v ){ loadData();
        }
        });
    }


    private void loadData() {
        ArrayList<String> theList = new ArrayList<>( ); Cursor data =myDB.getListContents();
        if (data.getCount()==0){
            Toast.makeText(Registor.this,"There is no content",Toast.LENGTH_SHORT).show();


        }
        else{


        }
    }
    private boolean DeleteData(String x) {
        return sqlite.delete(DatabaseHandler.TABLE_NAME,DatabaseHandler.COL2+"=?",new String[]{x})>0;
    }

    private void addData(String newEntry) {
        boolean insertData=myDB.addData(newEntry); if (insertData==true){
            Toast.makeText(Registor.this,"Data added",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(Registor.this,"Unsuccessful",Toast.LENGTH_SHORT).show();

        }
    }
}

