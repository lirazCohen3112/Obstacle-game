package com.example.obstaclegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView[][] game_IMG_stone;
    private ImageView[] game_IMG_car;
    private ImageView[] game_IMG_heart;

    private MaterialButton main_BTN_right;
    private MaterialButton main_BTN_left;

    private ImageView main_IMG_heart1;
    private ImageView main_IMG_heart2;
    private ImageView main_IMG_heart3;

    private ImageView main_IMG_stone1;
    private ImageView main_IMG_stone2;
    private ImageView main_IMG_stone3;
    private ImageView main_IMG_stone4;
    private ImageView main_IMG_stone5;
    private ImageView main_IMG_stone6;
    private ImageView main_IMG_stone7;
    private ImageView main_IMG_stone8;
    private ImageView main_IMG_stone9;
    private ImageView main_IMG_stone10;
    private ImageView main_IMG_stone11;
    private ImageView main_IMG_stone12;

    private ImageView main_IMG_car1;
    private ImageView main_IMG_car2;
    private ImageView main_IMG_car3;

    private GameManager gameManager;

    private int numRows = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        gameManager = new GameManager();

        game_IMG_car[0].setVisibility(View.INVISIBLE);
        game_IMG_car[2].setVisibility(View.INVISIBLE);

        for (int i = 0; i < numRows; i++){
            for (int t = 0; t < 3; t++)
            {
                game_IMG_stone[i][t].setVisibility(View.INVISIBLE);
            }
        }

        stoneObstacle();

        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveRight();
            }
        });

        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveLeft();
            }
        });

    }

    private void findViews() {
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);

        main_IMG_heart1 = findViewById(R.id.main_IMG_heart1);
        main_IMG_heart2 = findViewById(R.id.main_IMG_heart2);
        main_IMG_heart3 = findViewById(R.id.main_IMG_heart3);

        main_IMG_car1 = findViewById(R.id.main_IMG_car1);
        main_IMG_car2 = findViewById(R.id.main_IMG_car2);
        main_IMG_car3 = findViewById(R.id.main_IMG_car3);

        main_IMG_stone1 = findViewById(R.id.main_IMG_stone1);
        main_IMG_stone2 = findViewById(R.id.main_IMG_stone2);
        main_IMG_stone3 = findViewById(R.id.main_IMG_stone3);
        main_IMG_stone4 = findViewById(R.id.main_IMG_stone4);
        main_IMG_stone5 = findViewById(R.id.main_IMG_stone5);
        main_IMG_stone6 = findViewById(R.id.main_IMG_stone6);
        main_IMG_stone7 = findViewById(R.id.main_IMG_stone7);
        main_IMG_stone8 = findViewById(R.id.main_IMG_stone8);
        main_IMG_stone9 = findViewById(R.id.main_IMG_stone9);
        main_IMG_stone10 = findViewById(R.id.main_IMG_stone10);
        main_IMG_stone11 = findViewById(R.id.main_IMG_stone11);
        main_IMG_stone12 = findViewById(R.id.main_IMG_stone12);


        game_IMG_stone = new ImageView[][]{
                {main_IMG_stone1, main_IMG_stone5, main_IMG_stone9},
                {main_IMG_stone2, main_IMG_stone6, main_IMG_stone10},
                {main_IMG_stone3, main_IMG_stone7, main_IMG_stone11},
                {main_IMG_stone4, main_IMG_stone8, main_IMG_stone12}
        };

        game_IMG_car = new ImageView[]{
                main_IMG_car1,
                main_IMG_car2,
                main_IMG_car3
        };

        game_IMG_heart = new ImageView[]{
                main_IMG_heart3,
                main_IMG_heart2,
                main_IMG_heart1
        };
    }

    public void moveLeft() {
        if (gameManager.getCarLocation() == 1) {
            game_IMG_car[0].setVisibility(View.VISIBLE);
            game_IMG_car[1].setVisibility(View.INVISIBLE);
            game_IMG_car[2].setVisibility(View.INVISIBLE);
            gameManager.changeCarLocation(0);
        } else if (gameManager.getCarLocation() == 2) {
            game_IMG_car[0].setVisibility(View.INVISIBLE);
            game_IMG_car[1].setVisibility(View.VISIBLE);
            game_IMG_car[2].setVisibility(View.INVISIBLE);
            gameManager.changeCarLocation(1);
        }
    }

    public void moveRight() {
        if (gameManager.getCarLocation() == 1) {
            game_IMG_car[0].setVisibility(View.INVISIBLE);
            game_IMG_car[1].setVisibility(View.INVISIBLE);
            game_IMG_car[2].setVisibility(View.VISIBLE);
            gameManager.changeCarLocation(2);
        } else if (gameManager.getCarLocation() == 0) {
            game_IMG_car[0].setVisibility(View.INVISIBLE);
            game_IMG_car[1].setVisibility(View.VISIBLE);
            game_IMG_car[2].setVisibility(View.INVISIBLE);
            gameManager.changeCarLocation(1);
        }
    }

    public void stoneObstacle() {
        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            public void run() {
                movingStones();
                IsThereCollision();
                handler.postDelayed(this, delay);
            }
        }, delay);

    }

    public void startingNewObstacle() {
        Random r = new Random();
        int RndNum = r.nextInt(3);
        game_IMG_stone[0][RndNum].setVisibility(View.VISIBLE);
        gameManager.getDataStone()[0][RndNum] = 1;
    }

    public void movingStones(){
        for (int i = numRows-1 ; i >= 0 ; i--){
            for(int j = 0 ; j < 3 ; j++)
            {
                if (gameManager.getDataStone()[i][j] == 1 ){
                    if(i != 3)
                    {
                        game_IMG_stone[i][j].setVisibility(View.INVISIBLE);
                        gameManager.getDataStone()[i][j] = 0;
                        game_IMG_stone[i+1][j].setVisibility(View.VISIBLE);
                        gameManager.getDataStone()[i+1][j] = 1;
                    }
                    else
                    {
                        game_IMG_stone[i][j].setVisibility(View.INVISIBLE);
                        gameManager.getDataStone()[i][j] = 0;
                    }
                }
            }
        }
        startingNewObstacle();
    }

    void IsThereCollision(){
        for(int j = 0 ; j < 3 ; j++){
            if(gameManager.getDataStone()[numRows-1][j] == 1 && gameManager.getCarLocation() == j){
                gameManager.missingCounterLives();
                game_IMG_heart[gameManager.getLives()].setVisibility(View.INVISIBLE);

                Toast.makeText(this , "Oops , collision" , Toast.LENGTH_LONG).show();

                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {

                    v.vibrate(500);
                }
            }

        }

        if(gameManager.getLives() == 0 )
        {
            Toast.makeText(this , "Game over" , Toast.LENGTH_LONG).show();
        }
    }
}