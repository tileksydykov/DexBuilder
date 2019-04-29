package com.flaterlab.dexbuilder.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flaterlab.dexbuilder.LoginActivity;
import com.flaterlab.dexbuilder.MainActivity;
import com.flaterlab.dexbuilder.ProjectActivity;
import com.flaterlab.dexbuilder.R;

import org.objenesis.instantiator.basic.FailingInstantiator;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;
    private Activity parent;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public TextView textView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
            textView = v.findViewById(R.id.main_recycler_view_text);
        }
        public void bind(final String node, final Context context, final Activity parent){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(parent, ProjectActivity.class);
                    intent.putExtra("projectName", node);
                    parent.startActivity(intent);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainListAdapter(ArrayList<String> myDataset, Context c, Activity parent) {
        mDataset = myDataset;
        context = c;
        this.parent = parent;
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
        holder.bind(element, context, parent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
