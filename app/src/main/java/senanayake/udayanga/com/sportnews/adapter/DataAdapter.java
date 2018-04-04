package senanayake.udayanga.com.sportnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import senanayake.udayanga.com.sportnews.R;
import senanayake.udayanga.com.sportnews.model.News;

/**
 * Created by Udayanga on 3/12/2018.
 */

public class DataAdapter extends BaseAdapter {
    private ArrayList<News> news;
    private LayoutInflater layoutInflater;
    private Context context;

    public DataAdapter(Context context, ArrayList<News> news) {
        this.news = news;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        News news = (News) getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view, null);
            holder = new ViewHolder();

            holder.titleTextView = convertView.findViewById(R.id.titleTextView);
            holder.descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTextView.setText(this.news.get(position).getTitle());
        holder.descriptionTextView.setText(this.news.get(position).getDescription());
        holder.imageView.setImageBitmap(news.bitmap);
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

    }
}
