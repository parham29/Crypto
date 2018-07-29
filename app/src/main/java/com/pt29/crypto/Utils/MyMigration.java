package com.pt29.crypto.Utils;

import android.support.annotation.NonNull;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by parham on 3/3/2018.
 */

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0) {
            schema.create("WidgetModel")
                    .addField("appWidgetId", int.class)
                    .addField("coinID", String.class);
            oldVersion++;
        }


    }
}
