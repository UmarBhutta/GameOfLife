package quandoo.com.gameoflife.dependency.components;

import dagger.Component;
import quandoo.com.gameoflife.dependency.SingleActivity;
import quandoo.com.gameoflife.dependency.modules.ActivityModule;
import quandoo.com.gameoflife.dependency.modules.MainActivityModule;
import quandoo.com.gameoflife.ui.activities.MainActivity;
import quandoo.com.gameoflife.ui.fragments.GameFragment;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
@SingleActivity
@Component(dependencies = ApplicationComponent.class,modules ={ActivityModule.class, MainActivityModule.class} )
public interface MainActivityComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(GameFragment gameFragment);
}
