package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        final TextView welcome_msg = (TextView) findViewById(R.id.tvWelcome);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Button bInventory = (Button) findViewById(R.id.bInventory);
        final Button bSettings = (Button) findViewById(R.id.bSettings);
        final Button bBackLogin = (Button) findViewById(R.id.bBacktoLogin);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        /* TODO: name was null in output */
        String message = name + " ar atgriezšanos!";
        welcome_msg.setText(message);
        etUsername.setText(username);

        bBackLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(UserMain.this, MainActivity.class);
                UserMain.this.startActivity(registerIntent);
            }
        });

        bInventory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(UserMain.this, InventoryMenu.class);
                UserMain.this.startActivity(registerIntent);
            }
        });

        bSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(UserMain.this, UserSettings.class);
                UserMain.this.startActivity(registerIntent);
            }
        });

    }
}
