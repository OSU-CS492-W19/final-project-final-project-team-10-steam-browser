package com.example.steambrowser;

import android.net.Uri;

import com.google.gson.Gson;

import java.util.Random;

public class AppUtils {

    private final static String APP_URL = "http://api.steampowered.com/ISteamApps/GetAppList/v2";

    //appid (int), name (String), positive (int), average_forever (int), average_2weeks (int), price (String), discount (String)
    public static class App {
        public int appid;
        public String name;
    }

    public static class Apps {
        App[] apps;
    }

    public static class AppList {
        Apps applist;
    }

    public static String getAppUrl() {
        return Uri.parse(APP_URL)
                .toString();
    }



    public static int getRandomAppID (String json) {
        Gson gson = new Gson();
        AppList results = gson.fromJson(json, AppList.class);
        if (results != null) {
            int rnd = new Random().nextInt(results.applist.apps.length);
            return results.applist.apps[rnd].appid;
        }
        else {
            return 0;
        }
    }
}
