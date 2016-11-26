package com.automic.nguyendhoang.productdiary.Common.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Nguyen.D.Hoang on 11/24/2016.
 */

public class FileUtils {

    /**
     * Read file from Asset
     *
     * @param context     Context
     * @param fileName     name of file need to read
     * @return return json string.
     */
    public static String loadJsonFromAsset(Context context, String fileName) {
        String content = "{\"data\":";

        AssetManager am = context.getAssets();
        BufferedReader reader = null;
        try {
            InputStream is = am.open(fileName);

            reader = new BufferedReader(new InputStreamReader(is));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                content += mLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.d("jsoncontent", content);
        return content + "}";
    }



}
