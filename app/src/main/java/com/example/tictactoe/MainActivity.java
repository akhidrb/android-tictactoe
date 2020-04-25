package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int numOfButtons = 9;
    private boolean playerOneTurn = true;
    private List<Button> buttons = new ArrayList<>();

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
        resetGame();
    }

    private void playerTwoWins() {
        Toast.makeText(getApplicationContext(), "Player Two Wins!!", Toast.LENGTH_SHORT).show();
        resetGame();
    }
    
    private boolean checkWin() {
        return true;
    }


    private void resetGame() {
        for (int i = 0; i < numOfButtons; i++) {
            buttons.get(i).setText("");
        }
        playerOneTurn = true;
    }

}