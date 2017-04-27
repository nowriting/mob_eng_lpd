package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        final Button bBack = (Button) findViewById(R.id.bBacktoMenu3);

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(UserSettings.this, UserMain.class);
                UserSettings.this.startActivity(registerIntent);
            }
        });
    }
}
