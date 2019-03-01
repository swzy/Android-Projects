package gitmad.gitmadheatmap;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;

public class ActivityUserLoggedIn extends AppCompatActivity {

    FbAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        mAuth = new FbAuth();
        setContentView(R.layout.activity_user_logged_in);
    }

    public void logout( View view ) {
        mAuth.signUserOutAndReturnToLogin();
    }

}
