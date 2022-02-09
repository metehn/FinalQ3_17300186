package me.metehanersoy.finalq3_17300186;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityCore extends AppCompatActivity {

    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private Core v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_core);
        setTitle("CMSE419_Final_Q3");

        lv = findViewById(R.id.lv);

        v2 = new Core(this);

        showAll();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String person = list.get(position);
                deleteStudent(person);
                list.remove(position);
                adapter.notifyDataSetChanged();


            }
        });
    }

    private String[] columns = {"course"};
    private void showAll() {
        list = new ArrayList<>();

        SQLiteDatabase db = v2.getReadableDatabase();
        Cursor reading = db.query("MyTable", columns, null, null, null, null, null);

        while (reading.moveToNext()) {

            String na1 = reading.getString(reading.getColumnIndex("course"));
            list.add(na1);



        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);



    }

    private void deleteStudent(String s){

        if(s.trim().length() == 0){

            Toast.makeText(this, "Please enter valid course", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = v2.getReadableDatabase();
        Cursor c =  db.rawQuery("SELECT * FROM MyTable WHERE course = '" +s+"'",null);

        if (c.moveToFirst()){

            db.execSQL("DELETE FROM MyTable WHERE course = '"+s+"' ");
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please select valid course", Toast.LENGTH_SHORT).show();
        }



    }
}