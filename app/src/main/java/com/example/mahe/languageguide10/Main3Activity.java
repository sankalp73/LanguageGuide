package com.example.mahe.languageguide10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.layout.simple_spinner_item;


public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public Language[] l2 = new Language[1];
    public Language[] l1 = new Language[1];
    String[] to = new String[1];
    String[] from = new String[1];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final TextView tv = (TextView) findViewById(R.id.textView2);
        Button T = (Button) findViewById(R.id.button5);
        Spinner s= (Spinner) findViewById(R.id.spin);
        Spinner s1= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language, simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.language, simple_spinner_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(this);
        s.setOnItemSelectedListener(this);

        /*
        s1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);*/

        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




               // Language l1= Language.fromString(from);

                EditText e = (EditText) findViewById(R.id.editText);
                String textTranslate=e.getText().toString();
                // Toast.makeText(getApplicationContext(),"l1 is null",Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(),l1[0].toString(), Toast.LENGTH_LONG).show();
                Translate obj1= new Translate();
                String translated= obj1.start(textTranslate,l1[0],l2[0]);
                tv.setText(translated);

            }

        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
     //     Toast.makeText(getApplicationContext(),"LALA",Toast.LENGTH_LONG).show();
        Spinner spinner =(Spinner) parent;
        if (spinner.getId() == R.id.spin) {
            to[0] = (String) parent.getItemAtPosition(pos);// An item was selected. You can retrieve the selected item using
            //Toast.makeText(getApplicationContext(), to[0].substring(to[0].indexOf('(')+1, to[0].lastIndexOf(')')), Toast.LENGTH_LONG).show();
            l2[0] = Language.fromString(to[0].substring(to[0].indexOf('(')+1, to[0].lastIndexOf(')')));// parent.getItemAtPosition(pos)

        }
        else
        {
            from[0] = (String) parent.getItemAtPosition(pos);// An item was selected. You can retrieve the selected item using
            //Toast.makeText(getApplicationContext(),from[0].substring(from[0].indexOf('(')+1,from[0].lastIndexOf(')')),Toast.LENGTH_LONG).show();
            l1[0] =Language.fromString(from[0].substring(from[0].indexOf('(')+1,from[0].lastIndexOf(')')));

        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}