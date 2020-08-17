package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText p1name;//declaring variable for widget
    EditText p2name;//declaring variable for widget
    Button startgame;//declaring variable for widget


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startgame = findViewById(R.id.newgame);//assigning variable to the widget by ID

        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//onclick listener for our start game button


                p1name = findViewById(R.id.player1_name);//assigning variable to the widget by ID
                p2name = findViewById(R.id.player2_name);//assigning variable to the widget by ID


                String player1Name = p1name.getText().toString();//assigning users entry to string
                String player2Name = p2name.getText().toString();//assigning users entry to string

                if(player1Name.equals(""))//if user left p1 name blank
                {
                    player1Name="Player 1";//default name player 1 is given
                    p1name.setText("Player 1");
                }
                if(player2Name.equals(""))//if user left p2 name blank
                {
                    player2Name="Player 2";//default name player 2 is assigned
                    p2name.setText("Player 2");
                }


                Intent intent = new Intent(MainActivity.this,Gameplay.class);//new intent created

                intent.putExtra("Player 1",player1Name);//sent to next activity using intent

                intent.putExtra("Player 2",player2Name);//sent to next activity using intent

                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);//next activity started
                    finish();//current activity finished
                }
            }
        });


    }
    }
