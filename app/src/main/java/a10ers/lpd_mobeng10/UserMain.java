package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        final TextView welcome_msg = (TextView) findViewById(R.id.tvWelcome);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        /* TODO: name was null in output */
        String message = name + " ar atgriezšanos!";
        welcome_msg.setText(message);
        etUsername.setText(username);

    }
}
