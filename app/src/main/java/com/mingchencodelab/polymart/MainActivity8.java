package com.mingchencodelab.polymart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mingchencodelab.polymart.lab8.OnItemClickListener;
import com.mingchencodelab.polymart.lab8.ToDo;
import com.mingchencodelab.polymart.lab8.ToDoListAdapter;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity8 extends AppCompatActivity implements OnItemClickListener {
    public static final String TAG = "MainActivity8";
    Button btnRefresh;
    Button btnCreate;
    List<ToDo> toDoList;
    RecyclerView rcvToDoList;
    ToDoListAdapter toDoListAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        btnRefresh = findViewById(R.id.get);
        btnCreate = findViewById(R.id.create);
        rcvToDoList = findViewById(R.id.recyclerView);
        toDoList = new ArrayList<>();
        getAllToDoList();
        Log.d(TAG, "onCreate: " + toDoList.size());
        toDoListAdapter = new ToDoListAdapter(toDoList, this);
        rcvToDoList.setAdapter(toDoListAdapter);
        rcvToDoList.setLayoutManager(new LinearLayoutManager(this));
        btnRefresh.setOnClickListener(v -> {
            getAllToDoList();
//            toDoListAdapter.setToDoList(toDoList);
        });
        btnCreate.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("create todo");
            builder.setMessage("enter title and content");
            EditText etTitle = new EditText(this);
            EditText etContent = new EditText(this);
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(etTitle);
            layout.addView(etContent);
            etTitle.setHint("title");
            etContent.setHint("content");
            builder.setView(layout);
            builder.setPositiveButton("create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (etTitle.getText().toString().isEmpty() || etContent.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity8.this, "title or content is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    createToDo(etTitle.getText().toString(), etContent.getText().toString());
                }
            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
        });
    }

    private void createToDo(String title, String content) {
        String id = UUID.randomUUID().toString();
        ToDo toDo = new ToDo();
        toDo.setContent(title);
        toDo.setTitle(content);
        db.collection("ToDo").document(id).set(toDo)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity8.this, "tao thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity8.this, "tao that bai", Toast.LENGTH_SHORT).show();
                    }
                });
        getAllToDoList();


    }

    private void getAllToDoList() {
        toDoList.clear();
        db.collection("ToDo").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ToDo toDo = new ToDo();
                                toDo.setId(document.getId());
                                toDo.setTitle((String) document.getData().getOrDefault("title", "no title"));
                                toDo.setContent((String) document.getData().getOrDefault("content", "no content"));
                                Log.d(TAG, "onComplete: " + toDo.getTitle() + " " + toDo.getContent() + " " + toDo.getId());
                                toDoList.add(toDo);
                                toDoListAdapter.notifyDataSetChanged();
                            }
                        } else {
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("action with this ToDo");
        builder.setNeutralButton("canncel", (dialog, which) -> {
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteToDo(position);
            }
        }).setNegativeButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateToDo(position);
            }
        }).show();
    }

    private void updateToDo(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("update this ToDo");
        builder.setMessage("enter title and content");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText etTitle = new EditText(this);
        EditText etContent = new EditText(this);
        etTitle.setHint("title");
        etContent.setHint("content");
        layout.addView(etTitle);
        layout.addView(etContent);
        builder.setView(layout);
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (etTitle.getText().toString().isEmpty() || etContent.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity8.this, "title or content is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.collection("ToDo").document(toDoList.get(position).getId()).update("title", etTitle.getText().toString(), "content", etContent.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity8.this, "update thanh cong", Toast.LENGTH_SHORT).show();
                                getAllToDoList();
                            } else {
                                Toast.makeText(MainActivity8.this, "update that bai", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    private void deleteToDo(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("delete this ToDo");
        builder.setMessage("are you sure?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.collection("ToDo").document(toDoList.get(position).getId()).delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity8.this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                                getAllToDoList();
                            } else {
                                Toast.makeText(MainActivity8.this, "xoa that bai", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}