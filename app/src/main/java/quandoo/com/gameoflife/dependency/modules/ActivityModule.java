package quandoo.com.gameoflife.dependency.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import quandoo.com.gameoflife.dependency.SingleActivity;

/**
 * Created by malikumarbhutta on 6/11/16.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }



    @Provides
    @SingleActivity
    AppCompatActivity activity(){
        return  this.activity;
    }
}
