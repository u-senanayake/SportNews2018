package senanayake.udayanga.com.sportnews.model;

import android.graphics.Bitmap;

/**
 * Created by Udayanga on 3/12/2018.
 */

public class News {
    public String description;
    public String title;
    public  String link;
    public String img;
    public Bitmap bitmap;

    public News(String description, String title, String link, String img, Bitmap bitmap) {
        this.description = description;
        this.title = title;
        this.link = link;
        this.img = img;
        this.bitmap = bitmap;
    }

    public News(String description, String title, String link, String img) {
        this.description = description;
        this.title = title;
        this.link = link;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
