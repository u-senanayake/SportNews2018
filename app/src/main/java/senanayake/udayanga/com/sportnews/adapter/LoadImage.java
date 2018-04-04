package senanayake.udayanga.com.sportnews.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Udayanga on 3/12/2018.
 */

public class LoadImage extends AsyncTask<String, String, Bitmap> {
    Bitmap bitmap;

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
