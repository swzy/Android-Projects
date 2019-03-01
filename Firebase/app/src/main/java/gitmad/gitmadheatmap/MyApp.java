package gitmad.gitmadheatmap;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

/**
 * Class that is used throughout our code to get the application context.
 * This class allows us to use contexts in any location, but is mainly used in
 * non-activity instances, non-fragment instances, or instances that do not have an assoc. context.
 */
public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
