package com.example.javapj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.javapj.Tests.*;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = findViewById(R.id.showInfo);
        info.setText(DeviceInfo.getFullDeviceName());
    }

    public void allTests(View view) throws IOException{
        BcryptHashing.main();


    }

    public void selTests(View view) throws IOException {
        Switch base64Sw = findViewById(R.id.base64Switch);
        Switch brainfSw = findViewById(R.id.brainSwitch);
        Switch matSw = findViewById(R.id.matmulSwitch);
        TextView info = findViewById(R.id.showInfo);

        if (base64Sw.isChecked()){
            System.out.println("BASE64");
            Base64Test base64 = new Base64Test();
            base64.main();
            info.setText(Base64Test.results());
        }

        if(brainfSw.isChecked()){
            System.out.println("BRAINTEST");
            BrainTest.main();
            info.setText(BrainTest.results());
        }

        if(matSw.isChecked()){
            System.out.println("MATMUL");
            MatMulTest.main();
            info.setText(MatMulTest.results());
        }
    }
}

