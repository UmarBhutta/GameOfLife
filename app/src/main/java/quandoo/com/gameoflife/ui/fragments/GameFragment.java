package quandoo.com.gameoflife.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.Bind;
import quandoo.com.gameoflife.R;
import quandoo.com.gameoflife.dependency.components.MainActivityComponent;
import quandoo.com.gameoflife.observer.ActionObserver;
import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.ui.activities.MainActivity;
import quandoo.com.gameoflife.ui.customviews.GridView;
import quandoo.com.gameoflife.ui.presenter.GameActivityPresenter;
import quandoo.com.gameoflife.ui.presenter.GameFragmentPresenter;
import quandoo.com.gameoflife.ui.presenter.GameFragmentPresenterImp;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    GameFragmentPresenterImp presenter;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.mainContainer)
    RelativeLayout mainContainer;


    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_game;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }
    @Override
    public void onPause() {
        presenter.pause();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);

        if (presenter != null) {
            presenter.setName(mParam1);
        }

        GameActivityPresenter gameBoardActivityPresenter;
        MainActivity gameBoardActivity;
        try {
            gameBoardActivity = (MainActivity) getActivity();
            gameBoardActivityPresenter = gameBoardActivity.getActionSubject();
            gameBoardActivityPresenter.register((ActionObserver) presenter);
            presenter.setImp((ActionObserverImp) gameBoardActivity.getActionSubject());

            presenter.setScreenUtils(screenUtils);

        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement ActionSubject");
        }

        gridView.setUiInteractor(presenter.getInteractionImp());

        gameBoardActivityPresenter.register(presenter.getInteractionImp());
        presenter.getInteractionImp().setImp(
                (ActionObserverImp) gameBoardActivity.getActionSubject());


        gridView.setGridConfig(presenter.getGridConfig());
        gridView.animate().setStartDelay(1000).alpha(1).start();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState = presenter.onSaveInstanceState(outState);

        Log.i(getString(R.string.app_name),"onSaveInstanceState");
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            presenter.onViewStateRestored(savedInstanceState);
        }
    }


}
