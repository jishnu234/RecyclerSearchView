package com.example.recyclersearchview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    ArrayList<User> data;
    List<User> dataFull;
    public MyAdapter(ArrayList<User> arrayList) {

        data=arrayList;
        dataFull=new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {


        holder.text_name.setText(data.get(position).getName());
        holder.text_age.setText(Integer.toString(data.get(position).getAge()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_name,text_age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_age=itemView.findViewById(R.id.age);
            text_name=itemView.findViewById(R.id.name);
        }
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }



    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<User> exampleList=new ArrayList<>();
            if(constraint==null|| constraint.length()==0)
            {
                exampleList.addAll(dataFull);
            }
            else
            {

                String serchItem=constraint.toString().toLowerCase().trim();
                for(User data:dataFull)
                {
                    if(data.getName().toLowerCase().contains(serchItem))
                    {
                        exampleList.add(data);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=exampleList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            data.clear();
            data.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}
