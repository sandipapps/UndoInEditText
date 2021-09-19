package com.sandipbhattacharya.undoinedittext;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare View object references
    EditText etInput;
    TextView tvResult;
    // Define a string to store original text, that is, the text in TextView before
    // hitting the Backspace.
    String originalText = "";
    // Create a flag so that we can store the original text one time and right after hitting the
    // Backspace.
    boolean undoState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the View objects
        etInput = findViewById(R.id.etInput);
        tvResult = findViewById(R.id.tvResult);
        // Attach OnKeyListener with etInput
        etInput.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                // You can identify which key was pressed by checking keyCode value with
                // KeyEvent.KEYCODE
                if (i == KeyEvent.KEYCODE_DEL){
                    // This is for backspace
                    // If undoState is false, get the EditText content and store in originalText.
                    if(undoState == false){
                        originalText = etInput.getText().toString().trim();
                        // Also, change the flag undoState to true so that we can store
                        // the original text one time.
                        undoState = true;
                    }
                }
                return false;
            }
        });
    }

    public void getInput(View view) {
        // Get text from etInput
        String totalText = etInput.getText().toString().trim();
        // Get the index of
        // Call indexOf() method on totalText and pass "\n" as parameter.
        // indexOf() returns the position of the first occurrence of a specified character
        // in a string.
        int firstLineLength = totalText.indexOf("\n");
        // Next, call substring() method on totalText and pass 0 and firstLineLength as parameters.
        // This method returns new String object containing the substring of the given string from
        // specified startIndex to endIndex. In substring() method In case of substring() method
        // startIndex is inclusive and endIndex is exclusive.
        String firstLine = totalText.substring(0, firstLineLength);
        // Set the TextView with firstLine
        tvResult.setText(firstLine);
    }

    public void undo(View view) {
        // When undo button is pressed set the EditText with originalText.
        etInput.setText(originalText);
    }
}