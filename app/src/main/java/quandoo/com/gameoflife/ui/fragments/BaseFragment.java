package quandoo.com.gameoflife.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import quandoo.com.gameoflife.dependency.HasComponent;
import quandoo.com.gameoflife.utils.ScreenUtils;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public abstract class BaseFragment extends Fragment{

    @Inject
    ScreenUtils screenUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    protected abstract int getFragmentLayout();

    private void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }

    //get component by its type
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((HasComponent<T>) getActivity()).getComponent());
    }

}
