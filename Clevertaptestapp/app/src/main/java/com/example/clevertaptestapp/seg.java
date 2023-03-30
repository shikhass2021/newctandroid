package com.example.clevertaptestapp;

import android.util.Log;

import com.clevertap.android.sdk.Application;
import com.clevertap.android.sdk.CleverTapAPI;
import com.segment.analytics.Analytics;
import com.segment.analytics.android.integrations.clevertap.CleverTapIntegration;

import java.util.logging.Handler;

public class seg extends Application {

    private static final String TAG = String.format("%s.%s", "CLEVERTAP", seg.class.getName());
    private static final String WRITE_KEY = "ArDN8OUccCeveZ6ap3z95SZBBFxeuzwb"; //This you will receive under source in segment.
    private static final String CLEVERTAP_KEY = "CleverTap";
    public static boolean sCleverTapSegmentEnabled = false;
    private CleverTapAPI clevertap;
    private static Handler handler = null;

    @Override
    public void onCreate()
    {
        super.onCreate();

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);

        Analytics analytics = new Analytics.Builder(getApplicationContext(), WRITE_KEY)
                .logLevel(Analytics.LogLevel.VERBOSE)
                .use(CleverTapIntegration.FACTORY)
                .build();

        analytics.onIntegrationReady(CLEVERTAP_KEY, new Analytics.Callback<CleverTapAPI>()
        {
            @Override
            public void onReady(CleverTapAPI instance) {
                Log.i(TAG, "analytics.onIntegrationReady() called");
                cleverTapIntegrationReady(instance);
            }
        });
        Analytics.setSingletonInstance(analytics);
    }

    private void cleverTapIntegrationReady(CleverTapAPI instance)
    {
        instance.enablePersonalization();
        sCleverTapSegmentEnabled = true;
        clevertap = instance;
    }
}

