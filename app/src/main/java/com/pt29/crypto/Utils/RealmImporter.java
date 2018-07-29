package com.pt29.crypto.Utils;

/**
 * Created by parham on 2/15/2018.
 */

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pt29.crypto.Models.CoinApiModel;
import com.pt29.crypto.R;

import java.io.InputStream;

import io.realm.Realm;

/**
 * From json to realm database
 */
public class RealmImporter {

    public static void importFromJson(final Resources resources) {
        Realm realm = Realm.getDefaultInstance();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                InputStream inputStream = resources.openRawResource(R.raw.app);
                try {
                    realm.createAllFromJson(CoinApiModel.class, inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }
            }
        });
    }




}