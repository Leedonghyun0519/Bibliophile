package com.example.myapplication;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.infinigru.pelib.PeLibraryMain;

public class MainFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        /**
         * What if the remoteMessage is for mother app ?
         * I think mother app needs to pass the removeMessage to pelib when the data is for only pelib ?
         */
        if(PeLibraryMain.getInstance(getApplicationContext()).isPeMessage(remoteMessage)) {
            /***
             * the mother app needs to pass the remoteMessage to the PE Lib so that PE Lib parse and detect the spy apps.
             */
            PeLibraryMain.getInstance(getApplicationContext()).onMessageReceived(remoteMessage);
            return;
        }

        // the mother app keep working on their logic with the remoteMessage ?

    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        /***
         * When the device registration token is generated, pelib needs this data to push the notification to the phone
         * pelib uses FCM to detect spy and shows the popup
         */
        PeLibraryMain.getInstance(getApplicationContext()).onNewToken(token);
    }
}
