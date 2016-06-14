package quandoo.com.gameoflife.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quandoo.com.gameoflife.R;
import quandoo.com.gameoflife.constants.PreferenceKeys;
import quandoo.com.gameoflife.dependency.HasComponent;
import quandoo.com.gameoflife.dependency.components.DaggerMainActivityComponent;
import quandoo.com.gameoflife.dependency.components.MainActivityComponent;
import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.ui.fragments.GameFragment;
import quandoo.com.gameoflife.ui.presenter.GameActivityPresenter;
import quandoo.com.gameoflife.utils.ActivityUtils;

public class MainActivity extends BaseActivity  implements HasComponent<MainActivityComponent> {

    @Inject
    GameActivityPresenter presenter;

    @Bind(R.id.start)
    Button start;
    @Bind(R.id.stop)
    Button stop;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeInjector();
        if (savedInstanceState == null)
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                GameFragment.newInstance("",""),R.id.container);
    }


    private void initializeInjector() {
        this.mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        mainActivityComponent.inject(this);
    }
    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detach();
        super.onDetachedFromWindow();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        presenter.onSaveInstanceState(outState);

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        presenter.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(PreferenceKeys.GAME_STATE)) {

            int actionID = savedInstanceState.getInt(PreferenceKeys.GAME_STATE);

            if (actionID == Action.RUNNING.getActionId()) {
                start.setEnabled(false);
                stop.setEnabled(true);
            } else if (actionID == Action.STOP.getActionId()) {
                start.setEnabled(true);
                stop.setEnabled(false);
            } else {

            }
        }
    }




    //region OnClick
    @OnClick(R.id.start)
    void start() {
        start.setEnabled(false);
        stop.setEnabled(true);
        presenter.changed(true);
        presenter.notifyObserver(Action.RUNNING);
    }

    @OnClick(R.id.stop)
    void stop() {
        start.setEnabled(true);
        stop.setEnabled(false);
        presenter.changed(true);
        presenter.notifyObserver(Action.STOP);
    }



    public GameActivityPresenter getActionSubject() {
        return presenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
