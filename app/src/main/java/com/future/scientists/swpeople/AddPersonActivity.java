package com.future.scientists.swpeople;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextInputLayout tilName;
    private TextInputLayout tilPlanet;
    private TextInputLayout tilMass;
    private PersonGenerator generator = new PersonGenerator();
    private String avatarLink = "";

    public static Intent getStartIntent(Context context) {
        return new Intent(context, AddPersonActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ivAvatar = findViewById(R.id.ivAvatar);
        tilName = findViewById(R.id.tilName);
        tilPlanet = findViewById(R.id.tilPlanet);
        tilMass = findViewById(R.id.tilMass);
        loadImage();
        ivAvatar.setOnClickListener(view -> loadImage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_person, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionAdd) {
            String name = tilName.getEditText().getText().toString();
            String planet = tilPlanet.getEditText().getText().toString();
            String massString = tilMass.getEditText().getText().toString();
            int mass = massString.isEmpty() ? 0 : Integer.valueOf(massString);
            Person person = new Person(name, avatarLink, planet, mass);
            AppDatabase.getInstance(this).personDao().insertPerson(person);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void loadImage() {
        avatarLink = generator.getAvatarLink();
        Picasso.get().load(avatarLink)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .centerCrop()
                .into(ivAvatar);
    }
}
