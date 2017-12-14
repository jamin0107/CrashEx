package com.jamin.crash.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button button = findViewById(R.id.catch_btn);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        createNPE();
      }
    });
    Button uncaught = findViewById(R.id.uncatch_btn);
    uncaught.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String a = null;
        a.equals("");
      }
    });
  }

  public void createNPE() {
    new Thread(new Runnable() {
      @Override public void run() {
        String a = null;
        a.equals("");
      }
    }).start();
  }
}
