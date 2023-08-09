package com.mingchencodelab.polymart.lab8;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mingchencodelab.polymart.R;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>{
    public static final String TAG = "ToDoListAdapter";
    private final List<ToDo> toDoList;
    private OnItemClickListener onItemClickListener;

    public ToDoListAdapter(List<ToDo> toDoList, OnItemClickListener onItemClickListener) {
        this.toDoList = toDoList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList.clear();
        this.toDoList.addAll(toDoList);
        notifyDataSetChanged();
    }
    public List<ToDo> getToDoList() {
        return toDoList;
    }
    @NonNull
    @Override
    public ToDoListAdapter.ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);
        return new ToDoListViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ToDoListViewHolder holder, int position) {
        ToDo toDo = toDoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + toDo.getTitle());
        holder.tvTitle.setText(toDo.getTitle());
        holder.tvContent.setText(toDo.getContent());

    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public static class ToDoListViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvContent;
        public ToDoListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.lab8_tv_item_title);
            tvContent = itemView.findViewById(R.id.lab8_tv_item_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
