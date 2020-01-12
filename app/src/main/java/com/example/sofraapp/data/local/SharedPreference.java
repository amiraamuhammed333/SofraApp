package com.example.sofraapp.data.local;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.sofraapp.data.model.client.login_client.ClientData;
import com.example.sofraapp.data.model.restaurantt.restaurant_item.login_restaurant.ResataurantData;
import com.google.gson.Gson;


public class SharedPreference {

    private static SharedPreferences sharedPreferences = null;
    public static String USER_DATA_CLIENT = "USER_DATA_CLIENT";
    public static String USER_DATA_RESTAURANT = "USER_DATA_RESTAURANT";
    public static String REMEMBER = "REMEMBER";
    public static String PASSWORD_CLIENT = "PASSWORD_CLIENT";
    public static String EMAIL_CLIENT = "EMAIL_CLIENT";

    public static String NAME_RESTAURANT = "NAME_RESTAURANT";
    public static String EMAIL_RESTAURANT = "EMAIL_RESTAURANT";
    public static String REGION_RESTAURANT = "REGION_RESTAURANT";
    public static String DELIVERYCOST_RESTAURANT = "DELIVERYCOST_RESTAURANT";
    public static String MINIMUMCHARGER_RESTAURANT = "MINIMUMCHARGER_RESTAURANT";
    public static String CONFIRMATION_PASSWORD_RESTAURANT = "CONFIRMATION_PASSWORD_RESTAURANT";
    public static String PASSWORD_RESTAURANT = "PASSWORD_RESTAURANT";
    public static String DELIVERY_TIME_RESTAURANT = "DELIVERY_TIME_RESTAURANT";

    public static String USER_TYPE = "USER_TYPE";
    public static String CLIENT = "CLIENT";
    public static String RESTAURANT = "RESTAURANT";
    public static String UPDATE = "UPDATE";
    public static String NEW = "NEW";


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", activity.MODE_PRIVATE);
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }


    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }


    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static void saveUserDataClient(Activity activity, ClientData userDataClient) {
        SaveData(activity, USER_DATA_CLIENT, userDataClient);
    }

    public static ClientData loadUserDataClient(Activity activity) {
        setSharedPreferences(activity);
        ClientData userDataClient = null;
        Gson gson = new Gson();
        userDataClient = gson.fromJson(loadStringData(activity, USER_DATA_CLIENT), ClientData.class);
        return userDataClient;
    }


    public static void saveUserDataRestaurant(Activity activity, ResataurantData userDataRestaurant) {
        SaveData(activity, USER_DATA_RESTAURANT, userDataRestaurant);
    }

    public static String loadStringData(Activity activity, String data_key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }
        return sharedPreferences.getString(data_key, null);
    }


    public static boolean loadBooleanData(Activity activity, String data_key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }
        return sharedPreferences.getBoolean(data_key, false);
    }






    public static ResataurantData loadUserDataRestaurant(Activity activity) {
        setSharedPreferences(activity);

        ResataurantData userDataRestaurant = null;
        Gson gson = new Gson();
        userDataRestaurant = gson.fromJson(loadStringData(activity, USER_DATA_RESTAURANT), ResataurantData.class);
        return userDataRestaurant;

    }

    public static void clean(Activity activity) {

        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }

    }


}
