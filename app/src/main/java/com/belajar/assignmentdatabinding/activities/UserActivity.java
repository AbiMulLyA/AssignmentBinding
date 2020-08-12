package com.belajar.assignmentdatabinding.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.belajar.assignmentdatabinding.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class UserActivity extends AppCompatActivity {
    ImageView avatar;
    TextView email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        fullname = findViewById(R.id.tvName_user);
        email = findViewById(R.id.tvEmail_user);
        avatar = findViewById(R.id.profile_image_user);

        String fullName = getIntent().getStringExtra("firstName")+" "+getIntent().getStringExtra("lastName");
        String emailAd = getIntent().getStringExtra("email");
        String urlImg = getIntent().getStringExtra("avatar");

        fullname.setText(fullName);
        email.setText(emailAd);

        // load image from url
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.notfound);
        Glide.with(this).load(urlImg).apply(RequestOptions.centerCropTransform()).into(avatar);

    }
}