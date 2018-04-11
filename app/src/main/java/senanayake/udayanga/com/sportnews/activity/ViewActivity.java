package senanayake.udayanga.com.sportnews.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import senanayake.udayanga.com.sportnews.R;

public class ViewActivity extends AppCompatActivity {
    TextView viewTitle, viewDec;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setData();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void setData() {
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        Bitmap bitmap = intent.getParcelableExtra("bitmap");

        viewTitle = findViewById(R.id.viewTitle);
        viewDec = findViewById(R.id.viewDescription);
        imageView = findViewById(R.id.viewImageView);

        viewTitle.setText(title);
        viewDec.setText(description);
        imageView.setImageBitmap(bitmap);

    }


}
