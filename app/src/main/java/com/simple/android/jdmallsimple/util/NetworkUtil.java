package com.simple.android.jdmallsimple.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class NetworkUtil {

    public static String doGet(String urlPath){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    // url传入参数
    public static String doPost(String urlPath,HashMap<String, String> params){
        try {
            String paramStr="";
            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
            for (HashMap.Entry<String, String> entry : entrySet) {
                paramStr+=("&"+entry.getKey()+"="+entry.getValue());
            }
//			name=xxx&pwd=dsaad
            paramStr = paramStr.substring(1);

            URL url=new URL(urlPath+ "?" + paramStr);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String newPost(String urlPath,HashMap<String, String> params){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
//			--------------------------------------------
            String paramStr="";
            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
            for (HashMap.Entry<String, String> entry : entrySet) {
                paramStr+=("&"+entry.getKey()+"="+entry.getValue());
            }
//			name=xxx&pwd=dsaad
            paramStr = paramStr.substring(1);
//			--------------------------------------------
            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
