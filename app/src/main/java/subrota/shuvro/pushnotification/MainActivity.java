package subrota.shuvro.pushnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText titleET;
    private EditText messageET;
    private Button firstSendBTN, secondSendBTN;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = findViewById(R.id.titleET);
        messageET = findViewById(R.id.messageET);
        firstSendBTN = findViewById(R.id.sendThroughFirstChannel);
        secondSendBTN = findViewById(R.id.sendThroughSecondChannel);

        notificationManagerCompat =  NotificationManagerCompat.from(this);

        firstSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.getText().toString() != null && !titleET.getText().toString().equals("")){
                    if (messageET.getText().toString() != null && !messageET.getText().toString().equals("")){

                        Notification notification = new NotificationCompat.Builder(getApplicationContext(), Channels.FIRST_CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_notifications)
                                .setContentTitle(titleET.getText())
                                .setContentText(messageET.getText())
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();

                        notificationManagerCompat.notify(1, notification);

                    }else {
                        messageET.setError("Message is Empty.");
                    }
                }else {
                    titleET.setError("Title is Empty.");
                }
            }
        });

        secondSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (titleET.getText().toString() != null && !titleET.getText().toString().equals("")){
                    if (messageET.getText().toString() != null && !messageET.getText().toString().equals("")){

                        Notification notification = new NotificationCompat.Builder(getApplicationContext(), Channels.FIRST_CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_message)
                                .setContentTitle(titleET.getText())
                                .setContentText(messageET.getText())
                                .build();

                        notificationManagerCompat.notify(2, notification);

                    }else {
                        messageET.setError("Message is Empty.");
                    }
                }else {
                    titleET.setError("Title is Empty.");
                }

            }
        });
    }

}