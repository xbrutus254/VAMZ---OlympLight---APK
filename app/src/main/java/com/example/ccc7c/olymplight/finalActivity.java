package com.example.ccc7c.olymplight;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * is basic activity which starts after completing all levels and with single Menu button
 */
public class finalActivity extends AppCompatActivity {

    Button exitMenu;

    /**
     * shows background with winning quote and menu button
     * @param savedInstanceState - actual state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        exitMenu = (Button) findViewById(R.id.finaMenu);
        exitMenu.setBackgroundColor(Color.BLACK);
       // View v = findViewById(android.R.id.content).getRootView();
        //exitMenu = (ImageButton) findViewById(R.id.imageFinalButton);

        exitMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(finalActivity.this, Menu.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
