package com.future.scientists.swpeople;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PeopleActivity extends AppCompatActivity {

    private static final String TAG = PeopleActivity.class.getSimpleName();

    private PersonAdapter adapter;
    private PersonGenerator generator = new PersonGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_people);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            final PersonDao personDao = AppDatabase.getInstance(this).personDao();
            personDao.insertPerson(generator.getPerson());
            List<Person> people = personDao.getAll();
            adapter.update(people);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void initList() {
        RecyclerView rvPersons = findViewById(R.id.rvPersons);
        rvPersons.setHasFixedSize(true);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<Person> people = AppDatabase.getInstance(this).personDao().getAll();
        adapter = new PersonAdapter(people, person -> {
            final Intent intent = PersonActivity.getStartIntent(this, person.getId());
            startActivity(intent);
        });
        rvPersons.setAdapter(adapter);
    }
}
