package sg.edu.rp.c346.id22003619.movienight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
//                ivRating.setImageResource(R.drawable.rating_g);
                String imageUrl="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16277-28797ce.jpg?quality=90&webp=true&fit=584,471";
                Picasso.with(parent_context).load(imageUrl).resize(100, 100).into(ivRating);
                break;
            case "PG":
//                ivRating.setImageResource(R.drawable.rating_pg);
                String imageUrl2="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16278-28797ce.jpg?quality=90&webp=true&fit=584,471";
                Picasso.with(parent_context).load(imageUrl2).resize(100, 100).into(ivRating);
                break;
            case "PG13":
//                ivRating.setImageResource(R.drawable.rating_pg13);
                String imageUrl3="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16280-8d5bdb7.jpg?quality=90&webp=true&fit=320,320";
                Picasso.with(parent_context).load(imageUrl3).resize(100, 100).into(ivRating);
                break;
            case "NC16":
//                ivRating.setImageResource(R.drawable.rating_nc16);
                String imageUrl4="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16281-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
                Picasso.with(parent_context).load(imageUrl4).resize(100, 100).into(ivRating);
                break;
            case "M18":
//                ivRating.setImageResource(R.drawable.rating_m18);
                String imageUrl5="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16282-05127b2.jpg?quality=90&webp=true&fit=300,300";
                Picasso.with(parent_context).load(imageUrl5).resize(100, 100).into(ivRating);
                break;
            case "R21":
//                ivRating.setImageResource(R.drawable.rating_r21);
                String imageUrl6="https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16283-05127b2.jpg?quality=90&webp=true&fit=515,424";
                Picasso.with(parent_context).load(imageUrl6).resize(100, 100).into(ivRating);
                break;
        }
    }
        return rowView;
    }
}


