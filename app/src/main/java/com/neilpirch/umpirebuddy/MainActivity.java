package com.neilpirch.umpirebuddy;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int num_str = 0;
    private int num_ball = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View strike_button = findViewById(R.id.strike_button);
        strike_button.setOnClickListener(this);

        View ball_button = findViewById(R.id.ball_button);
        ball_button.setOnClickListener(this);
    }

    /** called when user presses "Ball" */
    public void onClick(View btn) {
        /** call different function for each button */
        switch (btn.getId()) {
            case R.id.ball_button:
                ball_button();
                break;
            case R.id.strike_button:
                strike_button();
                break;
        }

    }

    /** perform the actions to add a Ball */
    protected void ball_button() {
        num_ball++;
        /** after 4 balls, trigger a pop-up and reset */
        if (num_ball == 4) {
            AlertDialog.Builder result = new AlertDialog.Builder(this);
            result.setTitle("Walk");
            result.setMessage("WALK!");
            result.setCancelable(false);
            result.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    reset_count();
                }
            });
            result.show();
        }
        refresh_count();
    }

    /** perform the actions to add a Ball */
    protected void strike_button() {
        num_str++;
        /** after 3 strikes, trigger a pop-up and reset */
        if (num_str == 3) {
            AlertDialog.Builder result = new AlertDialog.Builder(this);
            result.setTitle("Out");
            result.setMessage("OUT!");
            result.setCancelable(false);
            result.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //The OK Button
                    reset_count();
                }
            });
            result.show();
        }
        refresh_count();
    }

    /** refresh the display to show current count */
    private void refresh_count() {
        TextView b = findViewById(R.id.ball_count);
        b.setText(String.format("%s", num_ball));
        TextView s = findViewById(R.id.strike_count);
        s.setText(String.format("%s", num_str));
    }


    /** reset ball and strikes to 0-0 */
    private void reset_count() {
        num_str = 0;
        num_ball = 0;
        refresh_count();
    }
}
