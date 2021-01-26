package com.example.javapj;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.javapj.Tests.*;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void allTests(View view){
        Base64Test base64 = new Base64Test();
        MatMulTest matMulTest = new MatMulTest();

        System.out.println("CLICKED");
        TextView info = (TextView) findViewById(R.id.showDeviceInfo);
        //info.setText(DeviceInfo.getFullDeviceName());
        //info.setText(MatMul.);
        //showTestResult.setText(Tests.overallTime);
        //overall.setText(results);
        //info.setText(Base64Java.results);
        //base64.main();
        matMulTest.main(null);




    }

}

