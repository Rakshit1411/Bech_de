package com.example.rakshit.bechde;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rakshit.bechde.home.OnListFragmentInteractionListener;
import com.example.rakshit.bechde.PostAd;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PostAd} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyhomeitemRecyclerViewAdapter extends RecyclerView.Adapter<MyhomeitemRecyclerViewAdapter.ViewHolder> {

    private final List<PostAd> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyhomeitemRecyclerViewAdapter(List<PostAd> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_homeitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).getName());
        holder.information.setText(mValues.get(position).getDesc());
        holder.date.setText(mValues.get(position).getDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public PostAd mItem;

        public TextView name,date;
        public TextView information,title;
        public ImageView img,profile;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView)view.findViewById(R.id.name);
            date = (TextView)view.findViewById(R.id.Date);
            information = (TextView)view.findViewById(R.id.information);
            //title = (TextView)view.findViewById(R.id.title);
            img = (ImageView)view.findViewById(R.id.imageView4);
        }


    }}
