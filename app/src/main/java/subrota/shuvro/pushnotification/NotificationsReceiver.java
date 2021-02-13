package subrota.shuvro.pushnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("getMessage");
        //here getMessage is using as key to get message form notification and toasting this as "to do something"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
