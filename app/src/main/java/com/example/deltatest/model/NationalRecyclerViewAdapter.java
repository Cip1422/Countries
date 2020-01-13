package com.example.deltatest.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deltatest.R;

import java.util.Collections;
import java.util.List;

public class NationalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView region_text;

        HeaderViewHolder(View itemView) {
            super(itemView);
            region_text = (TextView) itemView.findViewById(R.id.region);
        }

    }

    private static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView country_text;
        TextView capital_text;
        EventViewHolder(View itemView) {
            super(itemView);
            country_text = (TextView) itemView.findViewById(R.id.country_text);
            capital_text = (TextView) itemView.findViewById(R.id.capital_text);
        }

    }

    @NonNull
    private List<ListItem> items = Collections.emptyList();

    public NationalRecyclerViewAdapter(@NonNull List<ListItem> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                View itemView = inflater.inflate(R.layout.header_layout, parent, false);
                return new HeaderViewHolder(itemView);
            }
            case ListItem.TYPE_EVENT: {
                View itemView = inflater.inflate(R.layout.event_layout, parent, false);
                return new EventViewHolder(itemView);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                HeaderItem header = (HeaderItem) items.get(position);
                HeaderViewHolder holder = (HeaderViewHolder) viewHolder;

                holder.region_text.setText((header.getRegion().getRegion()));
                break;
            }
            case ListItem.TYPE_EVENT: {
                EventItem event = (EventItem) items.get(position);
                EventViewHolder holder = (EventViewHolder) viewHolder;

                holder.country_text.setText("Country: " + event.getName().getName());
                holder.capital_text.setText("Capital: " + event.getCapital().getCapital());
                //holder.
                break;
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

}