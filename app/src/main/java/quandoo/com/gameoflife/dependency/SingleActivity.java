package quandoo.com.gameoflife.dependency;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by malikumarbhutta on 6/11/16.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleActivity {
}
