package me.metehanersoy.finalq3_17300186;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextCode;
    RadioGroup RGroup;
    Button buttonAdd, buttonElective, buttonCore;

    private Core v2;
    private Elective v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CMSE419_Final_Q3");

        editTextCode = findViewById(R.id.editTextCode);
        RGroup = findViewById(R.id.RGroup);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonElective = findViewById(R.id.buttonElective);
        buttonCore = findViewById(R.id.buttonCore);

        v2 = new Core(this);
        v1 = new Elective(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextCode.getText().toString().isEmpty()){
                    int selectedRadioButtonId = RGroup.getCheckedRadioButtonId();
                    if (selectedRadioButtonId != -1){
                        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                        String selectedRbText = selectedRadioButton.getText().toString();
                        if(selectedRbText.equals("Elective")){
                            try {
                                addElective(editTextCode.getText().toString());
                            }finally {
                                v1.close();
                            }

                        }else{
                            try {
                                addCore(editTextCode.getText().toString());
                            }finally {
                                v2.close();
                            }
                        }


                    }else{
                        Toast.makeText(MainActivity.this, "Select A Button !!!! ", Toast.LENGTH_SHORT).show();
                    }


                }else{

                    Toast.makeText(MainActivity.this, "Enter Course!! ", Toast.LENGTH_SHORT).show();
                }



            }
        });

        buttonElective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityElective.class);
                startActivity(intent);
            }
        });


        buttonCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityCore.class);
                startActivity(intent);
            }
        });




    }

    private void addCore (String s){

        if(s.trim().length() == 0){

            Toast.makeText(this, "Please enter valid course", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = v2.getReadableDatabase();
        db.execSQL("INSERT INTO MyTable Values('"+s+"')");
        clearText();


    }
    private void addElective (String s){

        if(s.trim().length() == 0){

            Toast.makeText(this, "Please enter valid course", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = v1.getReadableDatabase();
        db.execSQL("INSERT INTO MyTable Values('"+s+"')");
        clearText();


    }
    private void clearText(){
        editTextCode.setText("");

    }
}