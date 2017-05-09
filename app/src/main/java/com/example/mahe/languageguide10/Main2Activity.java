package com.example.mahe.languageguide10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

import static android.R.layout.simple_spinner_item;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    public Language[] l2 = new Language[1];
    public Language[] l1 = new Language[1];
    String[] to = new String[1];
    String[] from = new String[1];


    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView textValue;
    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "Main2Activity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        statusMessage = (TextView)findViewById(R.id.status_message);
        textValue = (TextView)findViewById(R.id.text_value);

        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);

        findViewById(R.id.read_text).setOnClickListener(this);
        Spinner s= (Spinner) findViewById(R.id.spin);
        Spinner s1= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language, simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.language, simple_spinner_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view,
            int pos, long id) {

                to[0] = (String) parentView.getItemAtPosition(pos);// An item was selected. You can retrieve the selected item using
                //Toast.makeText(getApplicationContext(), to[0].substring(to[0].indexOf('(')+1, to[0].lastIndexOf(')')), Toast.LENGTH_LONG).show();
                l2[0] = Language.fromString(to[0].substring(to[0].indexOf('(')+1, to[0].lastIndexOf(')')));// parent.getItemAtPosition(pos)

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view,
                                       int pos, long id) {

                from[0] = (String) parentView.getItemAtPosition(pos);// An item was selected. You can retrieve the selected item using
                //Toast.makeText(getApplicationContext(),from[0].substring(from[0].indexOf('(')+1,from[0].lastIndexOf(')')),Toast.LENGTH_LONG).show();
                l1[0] =Language.fromString(from[0].substring(from[0].indexOf('(')+1,from[0].lastIndexOf(')')));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }









    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_text) {
            // launch Ocr capture activity.
            Intent intent = new Intent(this, ocrCaptureActivity.class);
            intent.putExtra(ocrCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(ocrCaptureActivity.UseFlash, useFlash.isChecked());

            startActivityForResult(intent, RC_OCR_CAPTURE);
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(ocrCaptureActivity.TextBlockObject);
                    statusMessage.setText("success");

                    Translate obj1= new Translate();
                    //Toast.makeText(getApplicationContext(),l1[0].toString(),Toast.LENGTH_LONG).show();
                    String translated= obj1.start(text,l2[0],l1[0]);
                    textValue.setText(translated);
                    //textValue.setText(text);
                    Log.d(TAG, "Text read: " + text);
                } else {
                    statusMessage.setText("failure");
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format("error",
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}