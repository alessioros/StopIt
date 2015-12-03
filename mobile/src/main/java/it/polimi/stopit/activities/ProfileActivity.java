package it.polimi.stopit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.Profile;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;


import it.polimi.stopit.R;
import it.polimi.stopit.model.User;

/**
 * Created by matteo on 03/12/15.
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User user=new User();
        user.setID(Profile.getCurrentProfile().getId());
        user.setName(Profile.getCurrentProfile().getFirstName());
        user.setSurname(Profile.getCurrentProfile().getLastName());

        CircularImageView profilepic=(CircularImageView) findViewById(R.id.profilepic);
        Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + user.getID() + "/picture?type=large").into(profilepic);
    }
}
