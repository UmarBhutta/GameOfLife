package quandoo.com.gameoflife.dependency.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import quandoo.com.gameoflife.dependency.SingleActivity;
import quandoo.com.gameoflife.dependency.modules.ActivityModule;

/**
 * Created by malikumarbhutta on 6/11/16.
 */
@SingleActivity
@Component(dependencies = ApplicationComponent.class , modules = ActivityModule.class)
public interface ActivityComponent {
    //Expose to the graphs
    AppCompatActivity activity();
}
