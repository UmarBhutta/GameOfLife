package quandoo.com.gameoflife.ui.presenter;

import android.os.Bundle;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public interface BasePresenter {

    void resume();

    void pause();

    void destroy();

    void detach();

    Bundle onSaveInstanceState(Bundle bundle);

    void onViewStateRestored(Bundle bundle);

    void onRestoreInstanceState(Bundle bundle);
}
