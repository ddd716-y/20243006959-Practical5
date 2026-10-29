package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY_NAME";

    private EditText editMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMunicipality = findViewById(R.id.editMunicipality);

        editMunicipality.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    processInput();
                    return true;
                }
                return false;
            }
        });

        // Clear the error hint as soon as the user starts typing again.
        editMunicipality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editMunicipality.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void processInput() {
        String input = editMunicipality.getText().toString();
        String name = TextProcessor.processMunicipality(input);

        if (name == null) {
            if (input == null || input.trim().isEmpty()) {
                editMunicipality.setError(getString(R.string.error_empty));
                Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            } else {
                editMunicipality.setError(getString(R.string.error_invalid));
                Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, name);
        startActivity(intent);
    }
}
