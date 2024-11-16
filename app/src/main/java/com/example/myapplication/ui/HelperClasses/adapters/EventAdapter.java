package com.example.myapplication.ui.HelperClasses.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.HelperClasses.EventHelperClass;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.FeaturedViewHolder> {

    ArrayList<EventHelperClass> locations;

    public EventAdapter(ArrayList<EventHelperClass> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview,parent,false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        EventHelperClass eventHelperClass = locations.get(position);
        holder.image.setImageResource(eventHelperClass.getImage());
        System.out.println(eventHelperClass.getImage());
        holder.title.setText(eventHelperClass.getTitle());
        holder.category.setText(eventHelperClass.getCategory());
        holder.description.setText(eventHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, category, description;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.cardview_image);
            title = itemView.findViewById(R.id.eventTitle);
            category = itemView.findViewById(R.id.eventCategory);
            description = itemView.findViewById(R.id.eventDescription);
        }
    }
}
