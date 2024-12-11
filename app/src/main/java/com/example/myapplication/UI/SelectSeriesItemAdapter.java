package com.example.myapplication.UI;

//import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.databinding.FragmentSelectSeriesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SelectSeriesItemAdapter extends RecyclerView.Adapter<SelectSeriesItemAdapter.ViewHolder> {




        private List<RaceSeries> itemList = new ArrayList<>();
        //private com.example.myapplication.RaceItemRecyclerViewAdapter.OnItemClickListener mListener;
        private OnItemClickListener mListener;

        public SelectSeriesItemAdapter(List<RaceSeries> items, OnItemClickListener mListener) {

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

            return new ViewHolder(FragmentSelectSeriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            RaceSeries item = itemList.get(position);
            //holder.textView.setText(item.getName());
            holder.bind(itemList.get(position), mListener);
            holder.mIdView.setText(String.valueOf(itemList.indexOf(item)+1));

        }

        // This method retrieves a drawable resource ID from its name
        /*public int getResourceIdFromString(Context context, String resourceName) {
            // Use 'drawable' as the resource type, change as needed
            //String resourceType = "drawable";
            //Make sure the string is all lowercase
            resourceName = resourceName.toLowerCase();
            //Remove any spaces in the string and replace with underscore
            resourceName = resourceName.replace(" ","_");

            // Use getIdentifier() to retrieve the resource ID
            //int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            //return resourceId;
            return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        }*/



        @Override
        public int getItemCount() {
            return itemList.size();
        }

        @NonNull
        @Override
        public String toString(){
            if (!itemList.isEmpty()) {
                return itemList.get(0).toString();
            }
            //System.out.println("itemlist size = 0");
            return itemList.toString();
        }

        public void addItem(RaceSeries raceSeries) {
            itemList.add(raceSeries);
            notifyItemInserted(itemList.size() - 1);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView mIdView;
            public final TextView mContentView;
            //public final ImageView imageView;

            public ViewHolder(FragmentSelectSeriesItemBinding binding) {
                super(binding.getRoot());
                mIdView = binding.itemNumber ;
                mContentView = binding.content;
                //imageView = binding.imageFlag;

            }

            public void bind(final RaceSeries item, final OnItemClickListener listener) {
                mContentView.setText(item.getName());
                //mIdView.setText(String.valueOf(itemList.indexOf(item)+1));
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                           // if (getAbsoluteAdapterPosition() != 0) {
                            listener.onItemClick(getAdapterPosition());
                            //listener.onItemClick(getAbsoluteAdapterPosition());
                            //}
                        }
                    }
                });
            }
        }

}