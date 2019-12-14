package com.example.catanddog.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catanddog.IOnClick;
import com.example.catanddog.R;
import com.example.catanddog.entities.Animal;
import com.example.catanddog.viewmodel.AnimalDataViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private Context context;
    private List<Animal> results;
    private AnimalDataViewModel mViewModel;
    private  IOnClick iOnClick;

    AnimalAdapter(Context mContext, AnimalDataViewModel mViewModel, IOnClick iOnClick) {
        context = mContext;
        results = new ArrayList<>();
        this.mViewModel = mViewModel;
        this.iOnClick = iOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvText.setText(results.get(position).getTitle());
        Picasso.get()
                .load(results.get(position).getUrl())
                .into(holder.imgIcon);
    }

    void getAnimalResults(List<Animal> list) {
        results.clear();
        results.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvText)
        TextView tvText;

        @BindView(R.id.imgIcon)
        ImageView imgIcon;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> {
                mViewModel.select(results.get(getAdapterPosition()));
                iOnClick.onClick(results.get(getAdapterPosition()));
            });
        }
    }
}
