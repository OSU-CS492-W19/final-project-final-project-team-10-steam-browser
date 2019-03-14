package com.example.steambrowser;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteamUtils {
    //steamspy.com/api.php?request=genre&genre={genreID}
    private final static String STEAM_GENRE_BASE_URL = "https://steamspy.com/api.php?request=genre";
    private final static String STEAM_GENRE_QUERY_PARAM = "genre";

    //appid (int), name (String), positive (int), average_forever (int), average_2weeks (int), price (String), discount (String)
    public static class Game {
        public int appid;
        public String name;
        public int positive;
        public int average_forever;
        public int average_2weeks;
        public String price;
        public String discount;
    }

    public static class SteamGenreResults {
        Map<String, Game> idGameMap = new HashMap<>();
//        public Game[] games;
    }

    public static String buildSteamGenreURL(String genre) {
        return Uri.parse(STEAM_GENRE_BASE_URL).buildUpon()
                .appendQueryParameter(STEAM_GENRE_QUERY_PARAM, genre)
                .build()
                .toString();
    }

    public static Game[] parseSteamGenreResults(String json) {
        Gson gson = new Gson();
        // it did not like it when the genre wasn't correct..
        //TODO:probably change to use a test query
        Map<String, Game> results = gson.fromJson(json, new TypeToken<Map<String,Game>>(){}.getType());
        if (results != null) {
            Game[] games = results.values().toArray(new Game[results.size()]);
            return games;
        }
        else {
            return null;
        }
    }
}
