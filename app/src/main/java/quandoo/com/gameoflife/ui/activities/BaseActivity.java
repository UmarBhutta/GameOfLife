package quandoo.com.gameoflife.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import quandoo.com.gameoflife.GameOfLife;
import quandoo.com.gameoflife.dependency.components.ApplicationComponent;
import quandoo.com.gameoflife.dependency.modules.ActivityModule;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);


    }

    protected ApplicationComponent getApplicationComponent() {
        return ((GameOfLife) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
