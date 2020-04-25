package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        if (playerOneTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        playerOneTurn = !playerOneTurn;
    }

    private void resetGame() {
        for (int i = 0; i < numOfButtons; i++) {
            buttons.get(i).setText("");
        }
        playerOneTurn = true;
    }

}