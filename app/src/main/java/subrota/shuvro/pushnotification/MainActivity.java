package subrota.shuvro.pushnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText titleET;
    private EditText messageET;
    private Button sendBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = findViewById(R.id.titleET);
        messageET = findViewById(R.id.messageET);
        sendBTN = findViewById(R.id.saveBTN);

        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.getText().toString() != null && !titleET.getText().toString().equals("")){
                    if (messageET.getText().toString() != null && !messageET.getText().toString().equals("")){

                    }else {
                        messageET.setError("Title is Empty");
                    }

                }else {
                    titleET.setError("Title is Empty");
                }
            }
        });
    }

}