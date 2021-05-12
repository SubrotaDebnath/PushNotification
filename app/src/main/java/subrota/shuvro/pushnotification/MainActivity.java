package subrota.shuvro.pushnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText titleET;
    private EditText messageET;
    private Button firstSendBTN, secondSendBTN;
    private NotificationManagerCompat notificationManagerCompat;
    //media session compat is for palette design
    //it is for under SDK_INT O
    private MediaSessionCompat mediaSessionCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = findViewById(R.id.titleET);
        messageET = findViewById(R.id.messageET);
        firstSendBTN = findViewById(R.id.sendThroughFirstChannel);
        secondSendBTN = findViewById(R.id.sendThroughSecondChannel);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        //tag is for debug  and one click action
        mediaSessionCompat = new MediaSessionCompat(this, "Tag");

        //Creating intent for onclick event on notifications
        Intent activityIntent = new Intent(this, MainActivity.class);
        //making content intent
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        //making Broadcast intent
        Intent broadcastIntent = new Intent(this, NotificationsReceiver.class);
        broadcastIntent.putExtra("getMessage", messageET.getText().toString().trim());
        //making action intent for on click action event
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //here flag (4th arg of above line)


        firstSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.getText().toString() != null && !titleET.getText().toString().equals("")) {
                    if (messageET.getText().toString() != null && !messageET.getText().toString().equals("")) {

                        //Here I using this method inside onclick for get message properly

                        //making Broadcast intent
                        Intent broadcastIntent = new Intent(v.getContext(), NotificationsReceiver.class);
                        broadcastIntent.putExtra("getMessage", messageET.getText().toString().trim());
                        //making action intent for on click action event
                        PendingIntent actionIntent = PendingIntent.getBroadcast(v.getContext(), 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        //here flag (4th arg of above line)

                        //convert drawable image into bitmap image
                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.profileimg);

                        Notification notification = new NotificationCompat.Builder(getApplicationContext(), Channels.FIRST_CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_notifications)
                                .setContentTitle(titleET.getText())
                                .setContentText(messageET.getText())
                                .setLargeIcon(largeIcon)
                                /*//setting notification preview style
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText(getString(R.string.description_text))
                                        .setBigContentTitle("Description")
                                        .setSummaryText("Push Notification demo")
                                )*/
                                .setStyle(new NotificationCompat.BigPictureStyle()
                                        //this is the picture when we expanded notification
                                        .bigPicture(largeIcon)
                                        // null for, I already set large icon in upper scope
                                        .bigLargeIcon(null))
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                //.setColor(Color.GREEN)
                                //applying content intent on click on notifications
                                .setContentIntent(contentIntent)
                                .setAutoCancel(true)
                                .setOnlyAlertOnce(true)
                                //adding on click action
                                .addAction(R.mipmap.ic_launcher, "Press to take action", actionIntent)
                                .build();

                        notificationManagerCompat.notify(1, notification);

                    } else {
                        messageET.setError("Message is Empty.");
                    }
                } else {
                    titleET.setError("Title is Empty.");
                }
            }
        });

        secondSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.profileimg);

                if (titleET.getText().toString() != null && !titleET.getText().toString().equals("")) {
                    if (messageET.getText().toString() != null && !messageET.getText().toString().equals("")) {

                        Notification notification = new NotificationCompat.Builder(getApplicationContext(), Channels.FIRST_CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_message)
                                .setContentTitle(titleET.getText())
                                .setContentText(messageET.getText())
                                .setLargeIcon(largeIcon)

                                //add action for expended mode
                                //in intent mode which is 3rd arg of add action method is onclick action on this icon
                                .addAction(R.drawable.ic_thumb_down, "Dislike", null)
                                .addAction(R.drawable.ic_previous, "Previous", null)
                                .addAction(R.drawable.ic_play, "Play", null)
                                .addAction(R.drawable.ic_next, "Next", null)
                                .addAction(R.drawable.ic_thumb_up, "Like", null)

                                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                        .setShowActionsInCompactView(1, 2, 3)
                                        .setMediaSession(mediaSessionCompat.getSessionToken())
                                )

                                .setSubText("This is a sub text")

                                /*//Can show maximum 7th position massage in ascending order
                                .setStyle(new NotificationCompat.InboxStyle()
                                        .addLine("This is the first message")
                                        .addLine("This is Second message")
                                        .addLine("This is third message")
                                        .addLine("This is fourth message")
                                        .addLine("This is fifth message")
                                        .addLine("This is sixth message")
                                        .addLine("This is seventh message")
                                        .setBigContentTitle("Preview")
                                        .setSummaryText("Push Notification demo"))*/

                                .build();

                        notificationManagerCompat.notify(2, notification);

                    } else {
                        messageET.setError("Message is Empty.");
                    }
                } else {
                    titleET.setError("Title is Empty.");
                }

            }
        });
    }

}