package com.example.javapj.Benchmarks;

import java.util.Arrays;
import java.util.Base64;

import static java.lang.System.out;


 public class Base64Test {

    final static int STR_SIZE = 131072;
    final static int TRIES = 8192;

    final static Base64.Decoder dec = Base64.getDecoder();
    final static Base64.Encoder enc = Base64.getEncoder();
    static byte[] str = new byte[0];
    static String str2;
    static int s_encoded;
    static double t_encoded;
    static double t_decoded;

     public static void main(){
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

        str = new byte[STR_SIZE];
        Arrays.fill(str, (byte)'a');
        str2 = enc.encodeToString(str);
        final byte[] encoded = str2.getBytes();
        final byte[] b_arr = dec.decode(encoded);

        s_encoded = 0;
        final long t = System.nanoTime();
        for (int i = 0 ; i < TRIES ; i++) {
            s_encoded += enc.encodeToString(str).length();
        }
        t_encoded = (System.nanoTime() - t) / 1e9;

        int s_decoded = 0;
        final long t1 = System.nanoTime();
        for (int i = 0 ; i < TRIES ; i++) {
            s_decoded += dec.decode(encoded).length;
        }
        t_decoded = (System.nanoTime() - t1) / 1e9;




        out.println(String.format("encode %s... to %s...: %d, %.2f", new String(Arrays.copyOf(str, 4)), str2.substring(0, 4), s_encoded, t_encoded));

        out.println(String.format("decode %s... to %s...: %d, %.2f", str2.substring(0, 4), new String(Arrays.copyOf(b_arr, 4)),s_decoded, t_decoded));

        out.println(String.format("overall time for the encoding and deconding of Base64 test: " + (t_encoded + t_decoded) + "s"));
    }

    public static String results(){

        return (String.format("overall time for Base64 test: " + (t_encoded + t_decoded) + "s"));


    }

}