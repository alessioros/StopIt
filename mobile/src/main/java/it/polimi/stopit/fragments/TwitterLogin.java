package it.polimi.stopit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import it.polimi.stopit.R;
import it.polimi.stopit.activities.ProfileActivity;

public class TwitterLogin extends Fragment {

    private TwitterLoginButton loginButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twitter_login, container, false);

        loginButton = (TwitterLoginButton) view.findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                Twitter.getApiClient(session).getAccountService().verifyCredentials(true, false, new Callback<User>() {

                    @Override
                    public void success(Result<User> userResult) {

                        User user = userResult.data;
                        String namesurname[]=user.name.split(" ");
                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                        intent.putExtra("userID", user.idStr);
                        intent.putExtra("username", user.screenName);
                        intent.putExtra("imageURL", user.profileImageUrl.replace("_normal",""));
                        intent.putExtra("name", namesurname[0]);
                        intent.putExtra("surname", namesurname[1]);

                        String msg = "@" + user.screenName + " logged in!";
                        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                        getActivity().startActivity(intent);
                    }

                    @Override
                    public void failure(TwitterException e) {

                    }

                });


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}


