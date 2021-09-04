package com.semihbaser.crypto;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.semihbaser.crypto.databinding.RecyclerRowBinding;

public class BelongingsHolder extends RecyclerView.ViewHolder {


    public RecyclerRowBinding binding;
    private Activity activity;


    public BelongingsHolder(RecyclerRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


}
