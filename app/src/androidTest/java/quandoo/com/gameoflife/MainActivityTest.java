package quandoo.com.gameoflife;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import quandoo.com.gameoflife.ui.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by malikumarbhutta on 6/14/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {



//    private MainActivity mainActivity;
//
//    public MainActivityTest(Class<MainActivity> activityClass) {
//        super(activityClass);
//    }
//
//    public MainActivityTest(){
//        super(MainActivity.class);
//    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

//    @Override
//    @Before
//    protected void setUp() throws Exception {
//        super.setUp();
//        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
//        mainActivity = getActivity();
//        GameOfLife gameOfLife = (GameOfLife) getInstrumentation().getTargetContext().getApplicationContext();
//        gameOfLife.getApplicationComponent().inject(mainActivity);
//
//    }

    @Test
    public void testStart(){

                onView(withId(R.id.start)).check(matches(isEnabled())).perform(click());


    }

    @Test
    public void testStop(){

                onView(withId(R.id.stop)).check(matches(isEnabled())).perform(click());

    }
}
