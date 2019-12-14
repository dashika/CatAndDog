package com.example.catanddog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catanddog.entities.Animal;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Animal animal = getIntent().getParcelableExtra(Animal.class.getSimpleName());
        if(animal!=null)
        {
            ((TextView)findViewById(R.id.tvText)).setText(animal.getTitle());
            Picasso.get()
                    .load(animal.getUrl())
                    .into(((ImageView)findViewById(R.id.imgIcon)));
        }
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
