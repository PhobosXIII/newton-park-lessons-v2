package com.future.scientists.swpeople;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class PersonActivity extends AppCompatActivity {

    private static final String EXTRA_PERSON = "com.example.phobos.roomtest.extras.EXTRA_PERSON";

    public static Intent getStartIntent(Context context, Person person){
        return new Intent(context, PersonActivity.class).putExtra(EXTRA_PERSON, person);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initUi();
    }

    private void initUi() {
        ImageView ivAvatar = findViewById(R.id.ivAvatar);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPlanet = findViewById(R.id.tvPlanet);
        TextView tvMass = findViewById(R.id.tvMass);

        final Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
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
