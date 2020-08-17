package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Gameplay extends AppCompatActivity implements View.OnClickListener {

    private Button[][] grid = new Button[3][3];//3x3 2d array of buttons for our tic tac toe board
    private int p1points;//int for player 1 points
    private int p2points;//int for player 2 points
    private boolean turn = true;//boolean to decide whose turn to play is
    private int count;
    String p1Name;//player 1 name
    String p2Name;//player 2 name
    private Button newgame;//variables for our widgets
    private TextView player1;//variables for our widgets
    private TextView player2;//variables for our widgets


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent intent = getIntent();//getting values for main activity
        p1Name = intent.getExtras().getString("Player 1");//value recieved using the key and stored in variable
        p2Name = intent.getExtras().getString("Player 2");//value recieved using the key and stored in variable
        newgame = findViewById(R.id.newgame);//assigning variable to widget using ID


        player1 = findViewById(R.id.player1);//assigning variable to widget using ID
        player2 = findViewById(R.id.player2);//assigning variable to widget using ID

        player1.setText(p1Name + ": " + p1points);//displaying player name and his score above the board
        player2.setText(p2Name+ ": " + p2points);//displaying player name and his score above the board

        for (int x = 0; x < 3; x++) {//double for loop to get IDs of our tic tac toe borad buttons
            for (int y = 0; y < 3; y++) {


             String gridID = "grid_"+ x + y;//string created for ID

                int resID = getResources().getIdentifier(gridID, "id", getPackageName());

                grid[x][y] = findViewById(resID);//finding view by ID and assigning to 2d array

                grid[x][y].setOnClickListener(this);//onlick listener for all our tic tac toe buttons


            }
        }

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on click listener for our new game button

                Intent intent = new Intent(Gameplay.this,MainActivity.class);//intent sending us back to home page
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {//onclick for our application

        if (!((Button) v).getText().toString().equals(""))//if blank then nothing
        {
            return;
        }

        if (turn)//if player 1s turn set the text of button to x
        {
            ((Button) v).setText("x");
        }
        else//if player 2s turn set the text to o
            {
            ((Button) v).setText("o");
        }

        count++;//counting the number of turns

        if (checkvictory())//function called which returns boolean value
        {
            if (turn) {//if player 1 turn he wins

                p1Wins();

            }
            else
            {
                p2Wins();//else player 2 wins
            }
        }
        else if (count == 9)
        {
            Draw();//if all 9 turns done and no winner then its a draw
        }
        else
            {
                turn = !turn;//changing turns from 1 player to other
        }

    }

    private boolean checkvictory() {//fucntion which checks in each step if a player has won
        String[][] allfields = new String[3][3];//new 2d array to check for winner

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                allfields[i][j] = grid[i][j].getText().toString();//2d loop getting text of all our buttons into new array
            }
        }

        for (int i = 0; i < 3; i++) {

            if (allfields[i][0].equals(allfields[i][1])//comparing if entries in a row are equal
                    && allfields[i][0].equals(allfields[i][2])
                    && !allfields[i][0].equals("")) {//checking if 1st column is not empty
                return true;//if all conditions satisfied we have a winner
            }
        }

        for (int i = 0; i < 3; i++) {

            if (allfields[0][i].equals(allfields[1][i])//comparing if entries in a column are equal
                    && allfields[0][i].equals(allfields[2][i])
                    && !allfields[0][i].equals("")) {//checking if 1st row is not empty
                return true;//if all conditions satisfied we have a winner
            }

        }

        if (allfields[0][0].equals(allfields[1][1])//checking entries for diagonal starting top left to bottom right
                && allfields[0][0].equals(allfields[2][2])
                && !allfields[0][0].equals("")) {
            return true;//if all conditions satisfied we have a winner
        }

        if (allfields[0][2].equals(allfields[1][1])//checking entries for diagonal starting top right to bottom left
                && allfields[0][2].equals(allfields[2][0])
                && !allfields[0][2].equals("")) {
            return true;//if all conditions satisfied we have a winner
        }

        return false;//else no winner
    }

    private void p1Wins() {//if player 1 wins

        p1points++;//his points are increased
        Toast.makeText(this, p1Name +" wins this game!", Toast.LENGTH_SHORT).show();//toast is displayed
        updatepoints();//function to update points
        resetgame();//function to reset game
    }

    private void p2Wins() {//if player 2 wins

        p2points++;//his points are increased
        Toast.makeText(this, p2Name +" wins this game!", Toast.LENGTH_SHORT).show();//toast is displayed
        updatepoints();//function to update points

        resetgame();//function to reset game
    }

    private void Draw() {//if its a draw
        Toast.makeText(this, " Its a Draw!", Toast.LENGTH_SHORT).show();//toast is displayed
        resetgame();//function to reset game
    }

    private void  resetgame(){//this is how we reset the game
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                grid[i][j].setText("");//set all button texts to blank
            }
        }

        count = 0;//number of turns player is made 0

        turn = true;//player turn set to player 1
    }

    private void updatepoints(){//this is how we update points using settext
        player1.setText(p1Name + ": " + p1points);
        player2.setText(p2Name + ": " + p2points);

    }
}
