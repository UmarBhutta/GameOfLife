package quandoo.com.gameoflife.dependency.modules;

import dagger.Module;
import dagger.Provides;
import quandoo.com.gameoflife.dependency.SingleActivity;
import quandoo.com.gameoflife.ui.presenter.GameActivityPresenter;
import quandoo.com.gameoflife.ui.presenter.GameActivityPresenterImp;
import quandoo.com.gameoflife.ui.presenter.GameFragmentPresenter;
import quandoo.com.gameoflife.ui.presenter.GameFragmentPresenterImp;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
@Module
public class MainActivityModule {
    @Provides
    @SingleActivity
    GameActivityPresenter provideGameBoardActivityPresenter() {
        return new GameActivityPresenterImp();
    }

    @Provides
    @SingleActivity
    GameFragmentPresenter provideGameBoardFragmentPresenter() {
        return new GameFragmentPresenterImp();
    }
}
