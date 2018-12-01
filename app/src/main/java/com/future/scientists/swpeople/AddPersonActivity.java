package com.future.scientists.swpeople;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextInputLayout tilName;
    private TextInputLayout tilPlanet;
    private TextInputLayout tilMass;
    private TextInputLayout tilDate;
    private TextInputLayout tilTime;
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
        tilDate = findViewById(R.id.tilDate);
        tilTime = findViewById(R.id.tilTime);

        loadImage(generator.getAvatarLink());
        ivAvatar.setOnClickListener(view -> loadImage(generator.getAvatarLink()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Person person = generator.getPerson();
            loadImage(person.getAvatar());
            tilName.getEditText().setText(person.getName());
            tilPlanet.getEditText().setText(person.getPlanet());
            tilMass.getEditText().setText(String.valueOf(person.getMass()));

            Date birthday = new Date(person.getBirthday());
            SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy"); // e.g. 24.10.2018
            tilDate.getEditText().setText(formatDate.format(birthday));
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm"); // e.g. 15:46
            tilTime.getEditText().setText(formatTime.format(birthday));
        });
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
            String dateTime = tilDate.getEditText().getText().toString() + " " + tilTime.getEditText().getText().toString();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm"); // e.g. 24.10.2018 15:46
            long birthday;
            try {
                birthday = format.parse(dateTime).getTime();
            } catch (ParseException e) {
                birthday = new Date().getTime();
                Log.e("AddPersonActivity", "Date parse error", e);
            }
            Person person = new Person(name, avatarLink, planet, mass, birthday);
            AppDatabase.getInstance(this).personDao().insertPerson(person);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void loadImage(String link) {
        avatarLink = link;
        Picasso.get().load(avatarLink)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .centerCrop()
                .into(ivAvatar);
    }
}
