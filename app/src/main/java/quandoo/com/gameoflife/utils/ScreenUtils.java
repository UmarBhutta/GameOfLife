package quandoo.com.gameoflife.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class ScreenUtils {

    private final Context context;

    public ScreenUtils(Context context) {
        this.context = context;
    }

    public int getDeviceWidth() {

        Point size = new Point();
        (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay())
                .getSize(size);
        return size.x;
    }

    public int getDeviceHeight() {

        Point size = new Point();
        (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay())
                .getSize(size);
        return size.y;
    }

    public int getOrientation() {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return Configuration.ORIENTATION_PORTRAIT;
        } else {
            return Configuration.ORIENTATION_LANDSCAPE;
        }
    }
}
