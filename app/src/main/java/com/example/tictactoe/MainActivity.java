package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int numOfButtons = 9;
    private boolean playerOneTurn = true;
    private List<Button> buttons = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < numOfButtons; i++) {
            String buttonId = "button_" + i;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            Button button = findViewById(resId);
            button.setOnClickListener(this);
            buttons.add(button);
        }

        Button resetButton = findViewById(R.id.reset_button);
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
        if (!buttonText.isEmpty()) return;

        if (playerOneTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        if (checkWin()) {
            if (playerOneTurn) playerOneWins();
            else playerTwoWins();
        }

        playerOneTurn = !playerOneTurn;
    }

    private void playerOneWins() {
        Toast.makeText(getApplicationContext(), "Player One Wins!!", Toast.LENGTH_SHORT).show();
        haltGame();
    }

    private void playerTwoWins() {
        Toast.makeText(getApplicationContext(), "Player Two Wins!!", Toast.LENGTH_SHORT).show();
        haltGame();
    }

    private boolean checkWin() {
        String[] fields = new String[9];
        //get text for all buttons
        for (int i = 0; i < 9; i++) {
            fields[i] = buttons.get(i).getText().toString();
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

    private void haltGame() {
        for (int i = 0; i < numOfButtons; i++) {
            buttons.get(i).setOnClickListener(null);
        }
    }


    private void resetGame() {
        for (int i = 0; i < numOfButtons; i++) {
            buttons.get(i).setText("");
        }
        playerOneTurn = true;
    }

}