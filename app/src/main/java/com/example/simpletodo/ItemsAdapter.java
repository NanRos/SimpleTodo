package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//responsible for displaying data from the model into a row in the recycler view (data->view)
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    public interface OnClickListener{
        void onItemClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener,
                        OnClickListener clickListener) {
        this.items = items;
        this.longClickListener=longClickListener;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    //responsible for creating each view
    //called when a new view is needed
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,
                parent,false);
        //wrap it inside a view holder and return it
        return new Viewholder(todoView);
    }

    @Override
    //responsible for taking data a particular position and putting into a view holder
    //
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        //grab the item at the position
        String item = items.get(position);
        //bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    //responsible for counting the number of items available in the data
    //tells recycler view how many items on the list
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views that represent each row of the list
    class Viewholder extends RecyclerView.ViewHolder{
        TextView tvItem;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1); //text view from simple list item
            // 1 layout
        }
        //update the view insider of the view holder with this data
        public void bind(String item) {

            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //NOTIFY THE LISTENER WHICH POSITION WAS LONG PRESSED.
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }

    }
}
