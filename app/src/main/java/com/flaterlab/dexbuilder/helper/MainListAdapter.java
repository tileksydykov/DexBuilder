package com.flaterlab.dexbuilder.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flaterlab.dexbuilder.R;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public TextView textView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
            textView = v.findViewById(R.id.main_recycler_view_text);
        }
        public void bind(final String node, final Context context){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(context, node, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainListAdapter(ArrayList<String> myDataset, Context c) {
        mDataset = myDataset;
        context = c;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MainListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_adapter_layout, parent, false);
        v.setElevation(8);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String element = mDataset.get(position);
        holder.textView.setText(element);
        holder.bind(element, context);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
