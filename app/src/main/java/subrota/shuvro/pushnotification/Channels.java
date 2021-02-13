package subrota.shuvro.pushnotification;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Channels extends Application {

    public static final String FIRST_CHANNEL_ID = "channel1";
    public static final String SECOND_CHANNEL_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel firstChannel = new NotificationChannel(
              FIRST_CHANNEL_ID,
              "First Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            firstChannel.setDescription("This is the first channel");

            NotificationChannel secondChannel = new NotificationChannel(
                    SECOND_CHANNEL_ID,
                    "Second Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            secondChannel.setDescription("This is the second channel");
        }
    }
}
