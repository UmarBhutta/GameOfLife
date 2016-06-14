package quandoo.com.gameoflife.dependency.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import quandoo.com.gameoflife.GameOfLife;
import quandoo.com.gameoflife.utils.ScreenUtils;

/**
 * Created by malikumarbhutta on 6/11/16.
 */
@Module
public class ApplicationModule {
    private GameOfLife gameOfLife;

    public ApplicationModule(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return this.gameOfLife;
    }

    @Provides
    @Singleton
    ScreenUtils provideScreenUtils(){
        return new ScreenUtils(gameOfLife.getApplicationContext());
    }
}
