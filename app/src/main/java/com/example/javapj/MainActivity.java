package com.example.javapj;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.javapj.Tests.MatMul;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void allTests(View view){
        new MatMul();
        System.out.println("CLICKED");
        TextView overall = (TextView) findViewById(R.id.showTestResult);
        //showTestResult.setText(Tests.overallTime);
        //overall.setText(results);


    }

}