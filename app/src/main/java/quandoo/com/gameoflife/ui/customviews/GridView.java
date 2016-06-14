package quandoo.com.gameoflife.ui.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import quandoo.com.gameoflife.ui.interactors.InteractionImp;
import quandoo.com.gameoflife.ui.viewconfigs.GridConfig;

/**
 * Created by malikumarbhutta on 6/12/16.
 */
public class GridView extends LinearLayout {

    RelativeLayout parentView;
    private InteractionImp uiInteractor;
    private GridConfig gridConfig;
    
    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        
    }

    public void setUiInteractor(InteractionImp uiInteractor) {
        this.uiInteractor = uiInteractor;
    }

    public void setGridConfig(GridConfig gridConfig) {
        this.gridConfig = gridConfig;
        init();
    }

    private void init() {
        if (gridConfig != null) {

            if (getLayoutParams() instanceof RelativeLayout.LayoutParams) {

                RelativeLayout.LayoutParams mainLP = (RelativeLayout.LayoutParams) getLayoutParams();
                mainLP.width = gridConfig.getWidth();
                mainLP.height = mainLP.width;
            }

            setBackgroundColor(gridConfig.getBackgroundColor());

            setGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);

            FrameLayout.LayoutParams parenLP = (FrameLayout.LayoutParams)
                    getParentView().getLayoutParams();

            parenLP.width = gridConfig.getWidth();
            parenLP.height = parenLP.width;

            addCells();
        }
    }

    private void addCells() {


        for (short i = 0; i < gridConfig.getColumnCellCount(); i++) {

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    gridConfig.getCellHeight()));


            for (short j = 0; j < gridConfig.getRowCellCount(); j++) {
                CellView cellView = new CellView(getContext(), j, i);
                cellView.setLayoutParams(new LinearLayout.LayoutParams(
                        gridConfig.getCellWidth(),
                        gridConfig.getCellHeight()));
                cellView.setColor(gridConfig.getDefaultCellColor());
                cellView.setBornColor(gridConfig.getNewBornCellColor());
                cellView.attach(uiInteractor);

                linearLayout.addView(cellView);

                uiInteractor.addCellView(cellView);

                getParentView().addView(getHorizontalView(j * gridConfig.getCellWidth()));
            }


            getParentView()
                    .addView(
                            getHorizontalView(
                                    gridConfig.getRowCellCount() *
                                            gridConfig.getCellWidth() -
                                            (int) convertDpToPixel(1)));


            addView(linearLayout);

            getParentView().addView(getVerticalView(i * gridConfig.getCellWidth()));
        }

        getParentView()
                .addView(
                        getVerticalView(
                                gridConfig.getColumnCellCount() *
                                        gridConfig.getCellWidth() -
                                        (int) convertDpToPixel(1)));
    }

    View getVerticalView(int x) {
        View view = new View(getContext());
        view.setBackgroundColor(gridConfig.getDefaultLineColor());
        view.setLayoutParams(new RelativeLayout.LayoutParams((int) convertDpToPixel(1),
                RelativeLayout.LayoutParams.MATCH_PARENT));
        view.setX(x);

        return view;
    }

    View getHorizontalView(int y) {
        View view = new View(getContext());
        view.setBackgroundColor(gridConfig.getDefaultLineColor());

        view.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) convertDpToPixel(1)
                )
        );
        view.setY(y);

        return view;
    }


    public float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return Math.round(px);
    }

    public RelativeLayout getParentView() {
        if (parentView == null) {
            parentView = (RelativeLayout) getParent();
        }
        return parentView;
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
