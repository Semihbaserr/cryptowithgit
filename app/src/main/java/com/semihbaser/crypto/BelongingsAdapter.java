package com.semihbaser.crypto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.semihbaser.crypto.databinding.RecyclerRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BelongingsAdapter extends RecyclerView.Adapter<BelongingsHolder> {

    private ArrayList<Belongings> belongingsArrayList;
    Activity activity;
    Context context;
    FirebaseFirestore firestore;


    public BelongingsAdapter(ArrayList<Belongings> belongingsArrayList, Activity activity) {
        this.belongingsArrayList = belongingsArrayList;
        this.activity = activity;
        this.context = activity.getApplicationContext();
        firestore = FirebaseFirestore.getInstance();

    }

    @NonNull

    @Override
    public BelongingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BelongingsHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BelongingsHolder holder, int position) {


        holder.binding.recyclerViewNameText.setText(belongingsArrayList.get(position).name);
        holder.binding.recyclerViewEmailText.setText("Uploaded By: " + belongingsArrayList.get(position).email);
        holder.binding.recyclerViewPriceText.setText(belongingsArrayList.get(position).price+ " ₺/Günlük");
        Picasso.get().load(belongingsArrayList.get(position).downloadUrl).into(holder.binding.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(activity, SomeActivity.class);
                intent.putExtra("name", belongingsArrayList.get(position));
                activity.startActivity(intent);
            }
        });

        holder.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();
                AlertDialog.Builder alert = new AlertDialog.Builder(holder.itemView.getContext());
                alert.setTitle("Are You Sure?");
                alert.setMessage("Deleted data cannot be retrieved!");
                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        firestore.collection("Belongings").document(belongingsArrayList.get(position).documentId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    belongingsArrayList.remove(belongingsArrayList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_LONG).show();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return belongingsArrayList.size();
    }


}