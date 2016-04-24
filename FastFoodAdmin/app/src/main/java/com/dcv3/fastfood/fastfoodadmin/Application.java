package com.dcv3.fastfood.fastfoodadmin;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

/**
 * Created by dezereljones on 4/23/16.
 */
public class Application extends android.app.Application {
    public Application(){
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //ParseObject.registerSubclass(Orders.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this,"Gokvsi0NGFg7I87lMaRgLjz6XILpfR1AofzzxKOF",
                "cmVUZO1VJRrGSRBbc3LmdYluCjxisZKv5nEIx1fl");
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
