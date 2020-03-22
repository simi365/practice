package com.example.myapplication;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class Task implements Runnable{

    private final String  API_KEY = "b669f4472edfb63960433b369c9d4685";
    private final String  ricrute = "c897754b31a30d1a";
    private final String URL ="api.openweathermap.org/data/2.5/weather?";
    String ret = "";

    public static String get(String endpoint, String encoding, Map<String, String> headers) throws IOException {

        final int TIMEOUT_MILLIS = 0;// タイムアウトミリ秒：0は無限
        final StringBuffer sb = new StringBuffer("");

        HttpURLConnection httpConn = null;
        BufferedReader br = null;
        InputStream is = null;
        InputStreamReader isr = null;


        try {
            URL url = new URL(endpoint);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(TIMEOUT_MILLIS);// 接続にかかる時間
            httpConn.setReadTimeout(TIMEOUT_MILLIS);// データの読み込みにかかる時間
            httpConn.setRequestMethod("GET");// HTTPメソッド
            httpConn.setUseCaches(false);// キャッシュ利用
            httpConn.setDoOutput(false);// リクエストのボディの送信を許可(GETのときはfalse,POSTのときはtrueにする)
            httpConn.setDoInput(true);// レスポンスのボディの受信を許可

            // HTTPヘッダをセット
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpConn.setRequestProperty(key, headers.get(key));// HTTPヘッダをセット
                }
            }
            httpConn.connect();
            final int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                is = httpConn.getInputStream();
                isr = new InputStreamReader(is, encoding);
                br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                // If responseCode is not HTTP_OK
            }

        } catch (IOException e) {
            throw e;
        } finally {
            // fortify safeかつJava1.6 compliantなclose処理
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return sb.toString();
    }

    @Override
    public void run() {
        request(10,10);

    }

    String request(int i, int n){
        Map<String,String> headers=new HashMap<String,String>();
        headers.put("X-Example-Header","Example-Value");
        String resultStr = "";
        try {
            resultStr = Task.get("http://webservice.recruit.co.jp/shingaku/school/v1/?key="+ricrute+"&pref_cd=12&pref_cd=13&keyword=%E6%95%B0%E5%AD%A6", "UTF-8", headers);
            Log.d("ゆーあーるえる","https://webservice.recruit.co.jp/shingaku/school/v1/"+ricrute );
            Log.d("結果", resultStr);
            ret = resultStr;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}
