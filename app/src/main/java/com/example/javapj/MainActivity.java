package com.example.javapj;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.javapj.Tests.MatMul;
import com.example.javapj.Tests.*;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;

import static java.lang.System.out;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void allTests(View view){
        Base64Java base64 = new Base64Java();
        System.out.println("CLICKED");
        TextView info = (TextView) findViewById(R.id.showDeviceInfo);
        //info.setText(DeviceInfo.getFullDeviceName());
        //info.setText(MatMul.);
        //showTestResult.setText(Tests.overallTime);
        //overall.setText(results);
        //info.setText(Base64Java.results);
        base64.main();


    }

}

class Base64Java {

    final static int STR_SIZE = 131072;
    final static int TRIES = 8192;

    final static Base64.Decoder dec = Base64.getDecoder();
    final static Base64.Encoder enc = Base64.getEncoder();
    public static int results;



    public void main(){
        for (final String[] fixture: new String[][]{
                {"hello", "aGVsbG8="}, {"world", "d29ybGQ="}
        }) {
            final String src = fixture[0];
            final String dst = fixture[1];
            final String encoded = enc.encodeToString(src.getBytes());
            if (!encoded.equals(dst)) {
                System.err.printf("%s != %s\n", encoded, dst);
                System.exit(1);
            }
            final String decoded = new String(dec.decode(dst));
            if (!decoded.equals(src)) {
                System.err.printf("%s != %s\n", decoded, src);
                System.exit(1);
            }
        }

        final byte[] str = new byte[STR_SIZE];
        Arrays.fill(str, (byte)'a');
        final String str2 = enc.encodeToString(str);
        final byte[] encoded = str2.getBytes();
        final byte[] b_arr = dec.decode(encoded);

        //notify("Java\t" + ProcessHandle.current().pid());

        int s_encoded = 0;
        final long t = System.nanoTime();
        for (int i = 0 ; i < TRIES ; i++) {
            s_encoded += enc.encodeToString(str).length();
        }
        final double t_encoded = (System.nanoTime() - t) / 1e9;

        int s_decoded = 0;
        final long t1 = System.nanoTime();
        for (int i = 0 ; i < TRIES ; i++) {
            s_decoded += dec.decode(encoded).length;
        }
        final double t_decoded = (System.nanoTime() - t1) / 1e9;



        out.println(
                String.format(
                        "encode %s... to %s...: %d, %.2f",
                        new String(Arrays.copyOf(str, 4)),
                        str2.substring(0, 4),
                        s_encoded, t_encoded));

        out.println(
                String.format("decode %s... to %s...: %d, %.2f",
                        str2.substring(0, 4),
                        new String(Arrays.copyOf(b_arr, 4)),
                        s_decoded, t_decoded));
        out.println("overall time: " + (t_encoded + t_decoded) + "s");


        out.println(String.format("overall time: " + (t_encoded + t_decoded) + "s"));
    }
}