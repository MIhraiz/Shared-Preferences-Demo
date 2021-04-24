package edu.birzeit.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox chkRem;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag) {
            String name = prefs.getString(NAME,"");
            String password = prefs.getString(PASS,"");
            edtUserName.setText(name);
            edtPassword.setText(password);
            chkRem.setChecked(true);
        }


    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        edtUserName = findViewById(R.id.edtTxtUserName);
        edtPassword = findViewById(R.id.edtTxtPassword);
        chkRem = findViewById(R.id.chkRem);
    }

    public void btnLogin_OnClick(View view) {

        String name = edtUserName.getText().toString();
        String password  = edtPassword.getText().toString();

        if (chkRem.isChecked()) {
            if(!flag){
                editor.putString(NAME,name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }
        }

    }
}