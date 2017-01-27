package io.tailorweb.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_FILE = "io.tailorweb.sharedpreferences.preferences";
    private static final String KEY_EDITTEXT = "key_edittext" ;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.mainEditText);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        String editTextString = mSharedPreferences.getString(KEY_EDITTEXT, "");
        mEditText.setText(editTextString);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.putString(KEY_EDITTEXT, mEditText.getText().toString());
        mEditor.apply();
        //  mEditor.clear(); will remove all the shared preferences
        //  mEditor.remove(KEY); will remove only the preference related to the key
        // then mEditor.apply();

        /* -- Quick Note --
            We could use commit instead of apply. The difference between
            the two is that commit() is instantaneous but
            performs disk writes. If you are on the ui thread you (like here)
            should call apply() which is asynchronous.
        */

    }
}
