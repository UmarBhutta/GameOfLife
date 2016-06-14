package quandoo.com.gameoflife;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by malikumarbhutta on 6/14/16.
 */
public class InjectedBaseActivityTest extends ActivityInstrumentationTestCase2 {



    public InjectedBaseActivityTest(Class activityClass) {
        super(activityClass);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

//        GameOfLife gameOfLife = (GameOfLife) getInstrumentation().getTargetContext().getApplicationContext();
//        gameOfLife.getApplicationComponent().inject();

    }

}
