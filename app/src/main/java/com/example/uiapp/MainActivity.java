package com.example.uiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepeat;
    private Button btnPickImage, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: started");

        if (validateData()) {
            if (agreementCheck.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(MainActivity.this, "Must agree to license agreement first", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: started");

        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility(View.GONE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender" + gender + "\n" +
                "Country " + country;

        Log.d(TAG, "showSnackBar: Snack bar text: " + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtPassRepeat.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: started");

        if (edtTxtName.getText().toString().equals("")) {
            txtWarnName.setText("Enter name");
            txtWarnName.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtTxtEmail.getText().toString().equals("")) {
            txtWarnEmail.setText("Enter email");
            txtWarnEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtTxtPassword.getText().toString().equals("")) {
            txtWarnPassword.setText("Enter password");
            txtWarnPassword.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtTxtPassRepeat.getText().toString().equals("")) {
            txtWarnPassRepeat.setText("Re-enter password");
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            return false;
        }
        if (!edtTxtPassword.getText().toString().equals(edtTxtPassRepeat.getText().toString())) {
            txtWarnPassRepeat.setText("Password do not match");
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void initViews() {

        Log.d(TAG, "initViews: started");

        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtPassRepeat = findViewById(R.id.edtTxtPassRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);

        countriesSpinner = findViewById(R.id.spinnerCountry);

        rgGender = findViewById(R.id.rgGender);

        agreementCheck = findViewById(R.id.agreementCheck);

        parent = findViewById(R.id.parent);
    }
}