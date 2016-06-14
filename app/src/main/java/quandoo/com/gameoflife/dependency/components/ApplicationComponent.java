package quandoo.com.gameoflife.dependency.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import quandoo.com.gameoflife.dependency.modules.ApplicationModule;
import quandoo.com.gameoflife.ui.activities.BaseActivity;
import quandoo.com.gameoflife.utils.ScreenUtils;

/**
 * Created by malikumarbhutta on 6/11/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //exposing to graph

    Context context();

    ScreenUtils screenUtils();

}
