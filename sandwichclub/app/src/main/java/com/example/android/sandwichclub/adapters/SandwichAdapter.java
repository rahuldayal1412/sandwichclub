package com.example.android.sandwichclub.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sandwichclub.DetailActivity;
import com.example.android.sandwichclub.R;
import com.example.android.sandwichclub.model.Sandwich;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder> {

    private final List<Sandwich> mSandwichList;

    public SandwichAdapter(List<Sandwich> sandwichList) {
        mSandwichList = sandwichList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Sandwich sandwich = mSandwichList.get(i);
        viewHolder.sandwichName.setText(sandwich.getMainName());
        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(viewHolder.sandwichImage);
    }

    @Override
    public int getItemCount() {
        return mSandwichList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sandwichName;
        ImageView sandwichImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            sandwichName = itemView.findViewById(R.id.sandwichName);
            sandwichImage = itemView.findViewById(R.id.sandwichImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchDetailActivity(v, getAdapterPosition());
                }
            });
        }

        private void launchDetailActivity(View v, int position) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, position);
            v.getContext().startActivity(intent);
        }
    }
}

