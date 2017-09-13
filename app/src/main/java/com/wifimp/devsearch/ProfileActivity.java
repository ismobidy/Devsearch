package com.wifimp.devsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ismob on 9/9/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // we initialize the View objects here.
        ImageView profileImageView = (ImageView) findViewById(R.id.profile_image);
        TextView userNameTextView = (TextView) findViewById(R.id.username);
        ImageButton shareProfile = (ImageButton) findViewById(R.id.share);
        TextView developerUrl = (TextView) findViewById(R.id.devs_url);

        //getting the passed intent

        Intent intent = getIntent();
        final String userName = intent.getStringExtra(DevsAdapter.KEY_NAME);
        String image = intent.getStringExtra(DevsAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(DevsAdapter.KEY_URL);

        //here we use picasso to load images into the defined imageView

        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        userNameTextView.setText(userName);
        developerUrl.setText(profileUrl);

        developerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here we set-up the onclick function of the profile url.
                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // this method is use for setting the share intent for the profile.
        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName + ", " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }
}






