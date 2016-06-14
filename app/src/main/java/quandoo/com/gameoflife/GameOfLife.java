package quandoo.com.gameoflife;

import android.app.Application;

import com.google.common.annotations.VisibleForTesting;

import quandoo.com.gameoflife.dependency.components.ApplicationComponent;
import quandoo.com.gameoflife.dependency.components.DaggerApplicationComponent;
import quandoo.com.gameoflife.dependency.modules.ApplicationModule;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class GameOfLife extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    protected ApplicationModule applicationModule() {
        return new ApplicationModule(this);
    }

    protected void initInjector(){
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    /**
     * Visible only for testing purposes.
     */
    @VisibleForTesting
    public void setTestComponent(ApplicationComponent appComponent) {
        applicationComponent = appComponent;
    }
}
