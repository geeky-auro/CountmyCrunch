package com.aurosaswatraj.countmycrunch.CalorieCounter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.aurosaswatraj.countmycrunch.R;

import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<MyView> {

    private List<String> list;

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item,
                        parent,
                        false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    // Constructor for adapter class
    // which takes a list of String type
    public FoodRecyclerViewAdapter(List<String> horizontalList)
    {
        this.list = horizontalList;
    }


}

class MyView extends RecyclerView.ViewHolder {
    TextView textView;
    public MyView(@NonNull View itemView) {
        super(itemView);
        // initialise TextView with id
        textView = itemView.findViewById(R.id.textview);
    }

}


// Override onCreateViewHolder which deals
// with the inflation of the card layout
// as an item for the RecyclerView.
