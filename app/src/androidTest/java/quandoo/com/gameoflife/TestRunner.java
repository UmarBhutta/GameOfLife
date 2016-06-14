package quandoo.com.gameoflife;

import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by malikumarbhutta on 6/14/16.
 */
public class TestRunner extends AndroidJUnitRunner
{
    @Override
    public void onCreate(Bundle arguments)
    {
        MultiDex.install(getTargetContext());
        super.onCreate(arguments);
    }
}
