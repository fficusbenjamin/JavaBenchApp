package com.example.javapj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.javapj.Tests.*;

import org.w3c.dom.Text;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    final long camera_time = System.currentTimeMillis();
    public static long time_diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = findViewById(R.id.showInfo);
        info.setText(DeviceInfo.getFullDeviceName());
    }

    public void cameraTest(View view) throws IOException{
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        startActivityForResult(intent, 1);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(data.getData());
            videoView.start();
            builder.setView(videoView).show();
            TextView info = findViewById(R.id.showInfo);
            time_diff = System.currentTimeMillis() - camera_time;
            info.setText(String.format("Time for the Camera test: %f s\n", time_diff / 1e3));
        }
    }


}

