package com.example.apptoast;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText pass;
    private Button btn, btn_alert, btn_act_change;
    private RatingBar ratingBar;
    private TextView text_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtn();
    }

    public void addListenerOnButtn() {
        pass = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        btn_alert = (Button) findViewById(R.id.other_button);
        btn_act_change = (Button) findViewById(R.id.newtab_button);

        //   btn.setText("Done");

        btn_act_change.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intend = new Intent("com.example.apptoast.SecondActivity");
                        startActivity(intend);
                    }
                }

        );

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(
                                MainActivity.this,
                                pass.getText(), Toast.LENGTH_SHORT

                        ).show();
                        if (!btn.getText().equals("Done")) {

                            btn.setText("Done");
                            btn.setBackgroundColor(Color.GREEN);

                        } else {
                            btn.setText("Показать");
                            btn.setBackgroundColor(Color.RED);
                        }
                    }
                });

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        text_show = (TextView) findViewById(R.id.textView);
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        text_show.setText(String.valueOf("Значение: " + rating)
                        );
                    }
                }


        );


        btn_alert.setOnClickListener(
                new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
//
                        AlertDialog.Builder a_bulder = new AlertDialog.Builder(MainActivity.this);
                        a_bulder.setMessage("Вы хотите закрыть приложение?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_bulder.create();
                        alert.setTitle("Закрытие приложения");
                        alert.show();
                    }
                }


        );

    }
}