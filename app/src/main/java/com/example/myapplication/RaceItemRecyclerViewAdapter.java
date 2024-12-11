package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.TestValues;
import com.example.myapplication.databinding.FragmentSelectRaceItemBinding;
import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RaceItemRecyclerViewAdapter extends RecyclerView.Adapter<RaceItemRecyclerViewAdapter.ViewHolder> {


    private List<Race> itemList = new ArrayList<>();
    private OnItemClickListener mListener;

    public RaceItemRecyclerViewAdapter(List<Race> items, OnItemClickListener mListener) {

        this.itemList = items;
        this.mListener = mListener;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSelectRaceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(itemList.get(position), mListener);
        Race item = itemList.get(position);
        //Get the application ID aka the context
        Context context = holder.imageView.getContext();
        //Update the textView itemNumber in fragment_select_race_item with race name
        holder.mIdView.setText(String.valueOf(itemList.indexOf(item)+1));
        //Update the textView content in fragment_select_race_item with race name
        holder.mContentView.setText(item.getRaceName());
        //Get the imageID for the particular race
        int imageResourceId = getResourceIdFromString(context, item.getCountry());
        //Update the image in fragment_select_race_item
        if (imageResourceId != 0) {
            Drawable drawable = context.getResources().getDrawable(imageResourceId, null);
            holder.imageView.setImageDrawable(drawable);
        }

    }

    // This method retrieves a drawable resource ID from its name
    public int getResourceIdFromString(Context context, String resourceName) {
        // Use 'drawable' as the resource type, change as needed
        String resourceType = "drawable";
        //Make sure the string is all lowercase
        resourceName = resourceName.toLowerCase();
        //Remove any spaces in the string and replace with underscore
        resourceName = resourceName.replace(" ","_");

        // Use getIdentifier() to retrieve the resource ID
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        return resourceId;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public String toString(){
        if (!itemList.isEmpty()) {
            return itemList.get(0).toString();
        }
        //System.out.println("itemlist size = 0");
        return itemList.toString();
    }

    public void addItem(Race race) {
        itemList.add(race);
        notifyItemInserted(itemList.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView imageView;

        public ViewHolder(FragmentSelectRaceItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            imageView = binding.imageFlag;

        }

        public void bind(final Race item, final OnItemClickListener listener) {
            mContentView.setText(item.getRaceName());
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            String eventDateString = null;
            eventDateString = HelperFunctions.getNextRaceEvent(item).getEventDate();
            LocalDateTime eventDate = LocalDateTime.now();
            if (eventDateString.length() > 20){
                eventDateString = new String(eventDateString.substring(0,eventDateString.indexOf('+')) + "Z");
                eventDate = LocalDateTime.parse( eventDateString.substring(0,eventDateString.length()-1),formatter);
            }else{
                eventDate = LocalDateTime.parse( eventDateString.substring(0,eventDateString.length()-1),formatter);
            }

            if(eventDate.isBefore(LocalDateTime.now())){
             mContentView.setTextColor(Color.GRAY);
            }else {
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        //if (getAbsoluteAdapterPosition() != 0) {
                            listener.onItemClick(getAdapterPosition());
                       //}
                    }
                }
            });
        }
    }


}