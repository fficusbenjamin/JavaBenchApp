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



    }

    public void allTests(View view) throws IOException {
        Switch base64Sw = (Switch) findViewById(R.id.base64Switch);
        Switch brainfSw = (Switch) findViewById(R.id.brainSwitch);
        Switch matSw = (Switch) findViewById(R.id.matmulSwitch);

        if (base64Sw.isChecked()){
            System.out.println("BASE64");
            Base64Test base64 = new Base64Test();
            base64.main();
        }

        if(brainfSw.isChecked()){
            System.out.println("BRAINTEST");
            BrainTest.main();
        }

        if(matSw.isChecked()){
            System.out.println("MATMUL");
            MatMulTest.main();
        }

        TextView info = (TextView) findViewById(R.id.showDeviceInfo);
        info.setText(DeviceInfo.getFullDeviceName());
        //info.setText(MatMul.);
        //showTestResult.setText(Tests.overallTime);
        //overall.setText(results);
        //info.setText(Base64Java.results);



    }

}

