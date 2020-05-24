package com.example.ccc7c.olymplight;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

/**
 * Is capital activity of this game Includes 16 colored cells in table, counter of steps and dark backgroud with bulb
 */
public class GameActivity extends AppCompatActivity  {

    /**
     * sensor variables
     */
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    /**
     * 16 buttons for gameplay
     * textview for showing steps
     * colors of their buttons
     * button restart to get back into the menu
     * counter for counting each click
     * restNext to indicate if level is complete
     * selectedlanguage for store, what language was chosen
     * level counter
     * text for welcome in next level
     */
    Button[][] buttons = new Button[4][4];
    private TextView tv;

    String[][] buttColor = new  String[4][4];

    Button restart;

    int counter=10;

    Boolean restNext = false;
    Random r;
    String selectedlanguage = "";

    int level = -1;
    String text = "";


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_game);
        //--------------------------------------------//
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        //--------------------------------------------//

        Bundle b = getIntent().getExtras();
        if (b != null) {
            level = b.getInt("key");
            selectedlanguage = b.getString("language");
        }

        if (selectedlanguage.equals("svk")) text = "";
        else if (selectedlanguage.equals("germ")) text = "";


        /**
         * finding button by their ID
         */
        buttons[0][0] = (Button) findViewById(R.id.button00);
        buttons[0][1] = (Button) findViewById(R.id.button01);
        buttons[0][2] = (Button) findViewById(R.id.button02);
        buttons[0][3] = (Button) findViewById(R.id.button03);
        buttons[1][0] = (Button) findViewById(R.id.button10);
        buttons[1][1] = (Button) findViewById(R.id.button11);
        buttons[1][2] = (Button) findViewById(R.id.button12);
        buttons[1][3] = (Button) findViewById(R.id.button13);
        buttons[2][0] = (Button) findViewById(R.id.button20);
        buttons[2][1] = (Button) findViewById(R.id.button21);
        buttons[2][2] = (Button) findViewById(R.id.button22);
        buttons[2][3] = (Button) findViewById(R.id.button23);
        buttons[3][0] = (Button) findViewById(R.id.button30);
        buttons[3][1] = (Button) findViewById(R.id.button31);
        buttons[3][2] = (Button) findViewById(R.id.button32);
        buttons[3][3] = (Button) findViewById(R.id.button33);
        restart = (Button) findViewById(R.id.bRestart);


        if (level > 0) createlevel();
        showStartDialog();
        tv = (TextView)findViewById(R.id.counter);
        tv.setTextColor(Color.RED);
        tv.setText("steps: " + String.valueOf(counter));


        /**
         * after click on slected button program inverts cells info their color
         */
        buttons[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(0, 0);
            }
        });
        buttons[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(0, 1);
            }
        });
        buttons[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(0, 2);
            }
        });
        buttons[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(0, 3);
            }
        });
        buttons[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(1, 0);
            }
        });
        buttons[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(1, 1);
            }
        });
        buttons[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(1, 2);
            }
        });
        buttons[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(1, 3);
            }
        });
        buttons[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(2, 0);
            }
        });
        buttons[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(2, 1);
            }
        });
        buttons[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(2, 2);
            }
        });
        buttons[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(2, 3);
            }
        });
        buttons[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(3, 0);
            }
        });
        buttons[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(3, 1);
            }
        });
        buttons[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(3, 2);
            }
        });
        buttons[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(3, 3);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameActivity.this, Menu.class);
                startActivity(myIntent);
                finish();
            }
        });
    }


    /**
     * After calling this function, program check range of levels after this,
     * opens file by selected language and starts to read line by line else program
     * go to the finalActivity and shows user that he wins from file program loads line
     * by actual level and save data into the variables by splitter. By selected data it
     * creates colored buutons and show message about actual level
     */
    public void createlevel() {
        if (level == 21)
        {
            Intent myIntent = new Intent(GameActivity.this, finalActivity.class);
            startActivity(myIntent);
            finish();
        }
        else {
            BufferedReader reader;
            String line = "";
            try{
                InputStream file = getAssets().open("inputtt.txt");
                if (selectedlanguage.equals("germ"))
                    file = getAssets().open("inputGer.txt");
                else if (selectedlanguage.equals("svk"))
                    file = getAssets().open("inputSvk.txt");
                reader = new BufferedReader(new InputStreamReader(file));
                line = reader.readLine();
                int i = 1;
                while(line != null){
                    line = reader.readLine();
                    if (i == level) {
                        break;
                    }
                    i++;
                }

            } catch(IOException ioe){
                ioe.printStackTrace();
            }

            String[] splitter = line.split("#", 5);
            counter = Integer.parseInt(splitter[1]);
            text = splitter[2];

            char[] ch = new char[splitter[0].length()];
            for (int i = 0; i < splitter[0].length(); i++) {
                ch[i] = splitter[0].charAt(i);
            }
            int moves = 0;
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++) {
                    if(Character.getNumericValue(ch[moves])   == 1) {
                        buttColor[i][j] = "red";
                    } else {
                        buttColor[i][j] = "green";
                    }
                    applyColor(buttons[i][j], buttColor[i][j]);
                    moves++;
                }
            }
        }
    }

    /**
     * Function that inverts color of selected button and theit
     * neighbours program does not change color to buttons which is on the edge
     * @param i - define row
     * @param j - define column
     */
    public void selectButton(int i, int j)
    {
        buttColor[i][j] = changeColor(buttons[i][j], buttColor[i][j]);
        if((i-1) >= 0)
            buttColor[i-1][j] = changeColor(buttons[i-1][j], buttColor[i-1][j]);
        if(i+1 < 4)
            buttColor[i+1][j] = changeColor(buttons[i+1][j], buttColor[i+1][j]);
        if(j-1 >= 0)
            buttColor[i][j-1] = changeColor(buttons[i][j-1], buttColor[i][j-1]);
        if(j+1 < 4)
            buttColor[i][j+1] = changeColor(buttons[i][j+1], buttColor[i][j+1]);

        counter-=1;
        tv = (TextView)findViewById(R.id.counter);
        tv.setTextColor(Color.RED);
        tv.setText("steps: " + String.valueOf(counter));

        checkGreen();
    }

    /**
     * Function change color to button in the parameter
     * @param b - is actual button
     * @param color - is color of button b
     * @return returns inverted color
     */
    public String changeColor(Button b, String color)
    {
        if(color != null || b != null)
        {
            if(color.equals("green"))
            {
                color = "red";
                b.setBackgroundColor(Color.RED);
                return color;
            }
            else if(color.equals("red"))
            {
                color = "green";
                b.setBackgroundColor(Color.GREEN);
                return color;
            }
        }
        return "";
    }

    /**
     * Function set color to the button by his title "color"
     * @param button - is selected button
     * @param color - is actual color of button
     */
    public void applyColor(Button button, String color)
    {
        if(color != null) {
            if (color.equals("green"))
                button.setBackgroundColor(Color.GREEN);
            else if (color.equals("red"))
                button.setBackgroundColor(Color.RED);
        }
    }

    /**
     * Function after each click check if all cells are green (means win) and if is,
     * it creates green wallpaper, change counter into the green and create
     * toast.makeText with win message Otherwise if steps are gone, it create
     * toast message with encouragement to try it again
     */
    public void checkGreen() {
        boolean is = true;
        outerloop:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (buttColor[i][j].equals("red")) {
                    is = false;
                    break outerloop;
                }
            }
        }

        if (is) {

            TextView tv = (TextView) findViewById(R.id.counter);
            tv.setTextColor(Color.GREEN);
            //counter = 0;
            if (counter >= 0) {
                if (selectedlanguage.equals("eng")) {
                    Toast.makeText(this, "You did it :)", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Shake for next level..)", Toast.LENGTH_SHORT).show();
                    restart.setText("MENU");
                }
                else if (selectedlanguage.equals("svk")){
                    Toast.makeText(this, "Podarilo sa ti to :)", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Potrasenim pokracujes..", Toast.LENGTH_SHORT).show();
                    restart.setText("MENU");
                }
                else {
                    Toast.makeText(this, "Du hast es geschafft :)", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Sie zittern weiter..", Toast.LENGTH_SHORT).show();
                    restart.setText("MENU");
                }
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bulb);
                View view = findViewById(R.id.mylayout);
                view.setBackground(new BitmapDrawable(bitmap));

                restNext = true;
            }
            else
            {
                //restart.setText("MENU");

                if (selectedlanguage.equals("eng"))
                    Toast.makeText(getApplicationContext(), "Almost :/ (steps gone)", Toast.LENGTH_SHORT).show();
                else if (selectedlanguage.equals("svk"))
                    Toast.makeText(getApplicationContext(), "Skoro :/ (mimo krokov)", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Fast: / (Schritte weg)", Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.broken);
                View view = findViewById(R.id.mylayout);
                view.setBackground(new BitmapDrawable(bitmap));
            }
        }
    }

    private void showStartDialog() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Level " + level)
                .setMessage(text)
                .setPositiveButton("ok", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

    }
    // ************************** SENSOR *********************** //
    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                //Toast.makeText(getApplicationContext(), "Restarted..", Toast.LENGTH_SHORT).show();

                if (!restNext) {
                    createlevel();
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light);
                    View view = findViewById(R.id.mylayout);
                    view.setBackground(new BitmapDrawable(bitmap));
                    TextView tv = (TextView)findViewById(R.id.counter);
                    tv.setTextColor(Color.RED);
                    tv.setText("steps: " + String.valueOf(counter));
                }
                else if (restNext) {
                    Intent myIntent = new Intent(GameActivity.this, GameActivity.class);
                    restNext = false;
                    Bundle b = new Bundle();
                    level++;
                    b.putInt("key", level);
                    b.putString("language", selectedlanguage); //Optional parameters
                    myIntent.putExtras(b);
                    startActivity(myIntent);
                    finish();
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
