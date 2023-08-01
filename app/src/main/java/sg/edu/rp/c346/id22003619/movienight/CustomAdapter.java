package sg.edu.rp.c346.id22003619.movienight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter <Movie>{
    Context parent_context;
    int layout_id;
    ArrayList<Movie> itemList;
    public CustomAdapter(Context context, int resource,
                         ArrayList<Movie> objects){
        super(context,resource,objects);
        parent_context=context;
        layout_id=resource;
        itemList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        ImageView ivRating = rowView.findViewById(R.id.imageView);
        if (tvTitle != null && tvYear != null && tvGenre != null) {

            Movie currentVersion = itemList.get(position);

        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        tvGenre.setText(currentVersion.getGenre());
        String rating = currentVersion.getRating();
        switch (rating) {
            case "G":
                ivRating.setImageResource(R.drawable.rating_g);
                break;
            case "PG":
                ivRating.setImageResource(R.drawable.rating_pg);
                break;
            case "PG13":
                ivRating.setImageResource(R.drawable.rating_pg13);
                break;
            case "NC16":
                ivRating.setImageResource(R.drawable.rating_nc16);
                break;
            case "M18":
                ivRating.setImageResource(R.drawable.rating_m18);
                break;
            case "R21":
                ivRating.setImageResource(R.drawable.rating_r21);
                break;
        }
    }
        return rowView;
    }
}


