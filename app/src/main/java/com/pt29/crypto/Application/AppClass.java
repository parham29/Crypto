package com.pt29.crypto.Application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;

import com.evernote.android.job.JobManager;
import com.pt29.crypto.Adapters.CoinListAdapter;
import com.pt29.crypto.R;
import com.pt29.crypto.Sync.SyncJobCreator;
import com.pt29.crypto.Utils.GlideApp;
import com.pt29.crypto.Utils.MyMigration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by parham on 2/11/2018.
 */

public class AppClass extends Application {


    @Override

    public void onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/samim.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        JobManager.create(this).addJobCreator(new SyncJobCreator());
        boolean overwriteDatabase = false;

        String DBName = "allcoins.realm";
        if (false){
            copyBundledRealmFile(this.getResources().openRawResource(R.raw.allcoins), DBName);
        }else{
            if (!fileFound(DBName, this.getFilesDir())){
                copyBundledRealmFile(this.getResources().openRawResource(R.raw.allcoins), DBName);
            }
        }
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DBName)
                .schemaVersion(0)
//                .schemaVersion(4) // Must be bumped when the schema changes
//                .migration(new MyMigration())
//                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
    private String copyBundledRealmFile(InputStream inputStream, String outFileName) {
        try {
            File file = new File(this.getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean fileFound(String name, File file) {
        File[] list = file.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.isDirectory()) {
                    fileFound(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    return true;
                }
            }
        return false;
    }

}
