package com.example.clevertaptestapp;

import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;





public class MainActivity extends AppCompatActivity implements CTInboxListener, DisplayUnitListener
{
    public Button btn, bt, b,c,A,sg, sg1,sg2;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override


    protected void onCreate(Bundle savedInstanceState) {

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"S69","Go to Retention","Go to your work chill",NotificationManager.IMPORTANCE_MAX,true);
        //push templates notification
        //inapp

        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
            

        }
        clevertapDefaultInstance.setDisplayUnitListener(this);

        //CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
//        CleverTapAPI.getDefaultInstance(this).pushNotificationViewedEvent(null);
        // How to add a sound file to your app : https://developer.clevertap.com/docs/add-a-sound-file-to-your-android-app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.s);
        bt=findViewById(R.id.bt);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        A=findViewById(R.id.A);
        sg=findViewById(R.id.sg);
        sg1=findViewById(R.id.segUser);
        sg2=findViewById(R.id.segCharged);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // each of the below mentioned fields are optional
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", "capt");    // String
                profileUpdate.put("Identity", 999);      // String or number
                profileUpdate.put("Email", "captain@america.com"); // Email address of the user
                profileUpdate.put("Phone", "+14155551234");   // Phone (with the country code, starting with +)
                profileUpdate.put("Gender", "F");             // Can be either M or F
                profileUpdate.put("DOB", new Date());         // Date of Birth. Set the Date object to the appropriate value first
                // optional fields. controls whether the user will be sent email, push etc.
                profileUpdate.put("MSG-email", false);        // Disable email notifications
                profileUpdate.put("MSG-push", true);          // Enable push notifications
                profileUpdate.put("MSG-sms", false);          // Disable SMS notifications
                profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications
                ArrayList<String> stuff = new ArrayList<String>();
                stuff.add("bag");
                stuff.add("shoes");
                profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings
                String[] otherStuff = {"Jeans","Perfume"};
                profileUpdate.put("MyStuff", otherStuff);                   //String Array

                clevertapDefaultInstance.onUserLogin(profileUpdate);




            }

        });

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        clevertapDefaultInstance.pushEvent("Shikha event");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // each of the below mentioned fields are optional
                // if set, these populate demographic information in the Dashboard
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", "AD");                  // String
                profileUpdate.put("Identity", 12);                    // String or number
                profileUpdate.put("Email", "AD@gmail.com");               // Email address of the user
                profileUpdate.put("Phone", "9987654323");                 // Phone (with the country code, starting with +)
                profileUpdate.put("Gender", "M");                           // Can be either M or F
                profileUpdate.put("DOB", new Date());                       // Date of Birth. Set the Date object to the appropriate value first

                profileUpdate.put("Photo", "www.foobar.com/image.jpeg");    // URL to the Image

                // optional fields. controls whether the user will be sent email, push etc.
                profileUpdate.put("MSG-email", false);                      // Disable email notifications
                profileUpdate.put("MSG-push", true);                        // Enable push notifications
                profileUpdate.put("MSG-sms", false);                        // Disable SMS notifications
                profileUpdate.put("MSG-dndPhone", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications
                profileUpdate.put("MSG-dndEmail", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications
                ArrayList<String> stuff = new ArrayList<String>();
                stuff.add("bag");
                stuff.add("shoes");
                profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings

                String[] otherStuff = {"Jeans","Perfume"};
                profileUpdate.put("MyStuff", otherStuff);                   //String Array

                clevertapDefaultInstance.pushProfile(profileUpdate);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // event with properties
                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Name", "Casio Chronograph Watch");
                prodViewedAction.put("Category", "Mens Accessories");
                prodViewedAction.put("Price", 59.99);
                prodViewedAction.put("Date", new java.util.Date());

                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> chargeDetails = new HashMap<String, Object>();
                chargeDetails.put("Amount", 300);
                chargeDetails.put("Payment Mode", "Credit card");
                chargeDetails.put("Charged ID", 24052013);

                HashMap<String, Object> item1 = new HashMap<String, Object>();
                item1.put("Product category", "books");
                item1.put("Book name", "The Millionaire next door");
                item1.put("Quantity", 1);

                HashMap<String, Object> item2 = new HashMap<String, Object>();
                item2.put("Product category", "books");
                item2.put("Book name", "Achieving inner zen");
                item2.put("Quantity", 1);

                HashMap<String, Object> item3 = new HashMap<String, Object>();
                item3.put("Product category", "books");
                item3.put("Book name", "Chuck it, let's do it");
                item3.put("Quantity", 5);

                ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
                items.add(item1);
                items.add(item2);
                items.add(item3);
                clevertapDefaultInstance.pushChargedEvent(chargeDetails, items);
                }
        });
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.showAppInbox();
            }
        });



        sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Analytics.with(getApplicationContext()).track("testEvent",
                        new Properties().putValue("value", "testValue") .putValue("testDate", new Date(System.currentTimeMillis()))
                );
            }
        });

        sg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Traits traits = new Traits();
                traits.putEmail("foo@foo.com");
                traits.putName("FooName");
                traits.putPhone("+14155551234");
                Analytics.with(getApplicationContext()).identify("666", traits, null);
//Replace all the example values with your required dynamic ones.
            }
        });
        sg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Properties properties = new  Properties();
                    properties.putValue("orderId", "444");
                    properties.putValue("revenue", 100);
                    properties.putProducts(
                            new Properties.Product("id1", "sku1", 100.0),
                            new Properties.Product("id2", "sku2", 200.0)
                    );

                    Analytics.with(getApplicationContext()).track("order completed", properties);
                }

        });




    }

    @Override
    public void inboxDidInitialize()
    {

            //ct.showAppInbox();//Opens Activity with default style configs
    }

    @Override
    public void inboxMessagesDidUpdate() {



    }

    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        CleverTapAPI.getDefaultInstance(this).pushEvent("helloooo whatssuppp");
        for (int i = 0; i <units.size() ; i++) {
            CleverTapDisplayUnit unit = units.get(i);
            prepareDisplayView(unit);
            Log.d("Clevertap","Native Display hurray"+units);
        }
    }

    private void prepareDisplayView(CleverTapDisplayUnit unit) {

    }


//
//    public void buttonClickFunction(View view){
//        Intent i =new Intent(getApplicationContext(),onuserlogin2.class);
//        startActivity(i);
//    }
}