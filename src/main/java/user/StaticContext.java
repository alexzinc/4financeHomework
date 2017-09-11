package user;

import java.util.HashMap;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class StaticContext {

    public void storeUser(String userAlias, User user) {
        ValueStore.store(userAlias, user);
    }

}
