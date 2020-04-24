package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[9];
    private Button resetButton;
    private boolean player1Turn = true;
    private int roundCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 9; i++) {
                String buttonID = "button_" + i;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i] = findViewById(resID);
                buttons[i].setText("");
                buttons[i].setOnClickListener(this);
        }

        resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        if (!buttonText.equals("")) return;
        else if (player1Turn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) player1Wins();
            else player2Wins();
        } else if (roundCount >= 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[] fields = new String[9];
        //get text for all buttons
        for (int i = 0; i < 9; i++) {
            fields[i] = buttons[i].getText().toString();
        }
        // Possible wins: 3 rows, 3 columns, 2 diagonal
        //check rows
        for (int i = 0; i <= 6; i+=3) {
            if (!fields[i].isEmpty() && fields[i].equals(fields[i+1]) && fields[i].equals(fields[i+2])) return true;
        }
        //check columns
        for (int i = 0; i <= 2; i++) {
            if (!fields[i].isEmpty() && fields[i].equals(fields[i+3]) && fields[i].equals(fields[i+6])) return true;
        }
        //check diagonals
        if (!fields[0].isEmpty() && fields[0].equals(fields[4]) && fields[0].equals(fields[8])) return true;
        if (!fields[2].isEmpty() && fields[2].equals(fields[4]) && fields[2].equals(fields[6])) return true;

        return false;
    }

    private void player1Wins() {
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        haltGameAction();
    }

    private void player2Wins() {
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        haltGameAction();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
    }

    private void haltGameAction() {
        for (int i=0; i < 9; i++) {
            buttons[i].setOnClickListener(null);
        }
    }

    private void startGameAction() {
        for (int i=0; i < 9; i++) {
            buttons[i].setOnClickListener(this);
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        roundCount = 0;
        player1Turn = true;
        startGameAction();
    }

}