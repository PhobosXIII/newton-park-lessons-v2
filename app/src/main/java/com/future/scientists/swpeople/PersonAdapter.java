package com.future.scientists.swpeople;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{
    private List<Person> people;

    public PersonAdapter(List<Person> people) {
        this.people = people;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person, viewGroup, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
        personViewHolder.bind(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people == null ? 0 : people.size();
    }

    public void add(Person person){
        people.add(person);
        notifyDataSetChanged();
    }

    public void update(List<Person> people){
        this.people.clear();
        this.people.addAll(people);
        notifyDataSetChanged();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{

        ImageView ivAvatar;
        TextView tvName;
        TextView tvPlanet;
        TextView tvMass;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvPlanet = itemView.findViewById(R.id.tvPlanet);
            tvMass = itemView.findViewById(R.id.tvMass);
        }

        public void bind(final Person person){
            Picasso.get().load(person.getAvatar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .centerCrop()
                    .into(ivAvatar);
            tvName.setText(person.getName());
            tvPlanet.setText(person.getPlanet());
            tvMass.setText(String.valueOf(person.getMass()));
        }
    }

}
