package com.example.ccc7c.olymplight;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Main menu default called by program after app starts
 */
public class Menu extends AppCompatActivity {

    /**
     * Main menu with four buttons
     */
    ImageButton start, info, score, ext;
    String selectedLanguage = "eng";
    String text, information;


    /**
     *
     * @param savedInstanceState  - actual state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //View v = findViewById(android.R.id.content).getRootView();

        start = (ImageButton) findViewById(R.id.imageButtonStart);
        score = (ImageButton) findViewById(R.id.imageButtonScore);
        info = (ImageButton) findViewById(R.id.imageButtonInfo);
        ext = (ImageButton) findViewById(R.id.imageButtonEnd);


        /**
         * start OnClick create new intent and bundle to move on into the GameActivity
         */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.content_game);
                //showStartDialog();
                String value = "";
                Intent myIntent = new Intent(Menu.this, GameActivity.class);
                Bundle b = new Bundle();
                myIntent.putExtra("key", 1); //Optional parameters
                myIntent.putExtra("language", selectedLanguage); //Optional parameters
                startActivity(myIntent);
                finish();
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                // Array of languages u can choose
                String[] languages = new String[]{"English", "German", "Slovak"};

                final List<String> languageList = Arrays.asList(languages);
                builder.setTitle("Select Language: ")
                        //.setMessage("You can buy our products without registration too. Enjoy the shopping")
                        .setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) selectedLanguage = "eng";
                                else if (which == 1) selectedLanguage = "germ";
                                else  selectedLanguage = "svk";
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();

            }
        });
        /**
         * by selected language program choose between info text from 3 versions
         */
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedLanguage.equals("eng")){
                    information = "How to play..";
                    text = "The task is to invert all colors to green. If you complete it in a certain number of steps " +
                            "the light bulb will light up, otherwise it will burst. If you manage to light the light bulb, " +
                            "shake your mobile phone to continue to the next level. If you want to restart the level, shake it again. " +
                            "Colors are inverted as follows: compressed, top, bottom, right and left. Beware of corners." ;
                }
                else if (selectedLanguage.equals("germ")){
                    information = "Spielanleitung..";
                    text = "Die Aufgabe besteht darin, alle Farben in Grün umzukehren. Wenn Sie dies in einer bestimmten Anzahl von Schritten erledigen"+
                            "Die Glühbirne leuchtet auf, andernfalls platzt sie. Wenn Sie es schaffen, die Glühbirne anzuzünden," +
                            "Schütteln Sie Ihr Mobiltelefon, um mit dem nächsten Level fortzufahren. Wenn Sie das Level neu starten möchten, schütteln Sie es erneut." +
                            "Die Farben werden wie folgt invertiert: komprimiert, oben, unten, rechts und links. Vorsicht vor Ecken.";
                }
                else {
                    information = "Návod ako hrať..";
                    text = "Úlohou je invertovať všetky farby do zelenej. Ak to splníš na určitý počet krokov" +
                            " žiarovka sa rozosvietí, ináč praskne. Ak sa ti podarí rozosvietiť žiarovku, " +
                            "pre pokračovanie do ďalšieho levela zatras mobilom. Ak cheš level reštartovať, opäť ním potras." +
                            "farby sa invertujú následovne: stlačená, horná, dolná, pravá a ľavá. Pozor na rohy.";
                }
                showStartDialog();
            }
        });
        /**
         * if is clicked exit, program will close
         */
        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finishActivity();
                System.exit(0);
            }
        });


    }

    /**
     * simple dialog for showing info about game
     */
    private void showStartDialog() {
        new android.app.AlertDialog.Builder(this)
                .setTitle(information)
                .setMessage(text)
                .setPositiveButton("ok", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

    }


}
