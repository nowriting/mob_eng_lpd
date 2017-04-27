package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        final Button bBack = (Button) findViewById(R.id.bBacktoMenu3);

        final EditText etID = (EditText) findViewById(R.id.etUserID);
        final EditText etName = (EditText) findViewById(R.id.etChangeName);
        final EditText etUsername = (EditText) findViewById(R.id.etChangeUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etChangePassword);
        final Button bRegister = (Button) findViewById(R.id.bRegisterChange);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user_id = etID.getText().toString();
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                Intent intent = new Intent(UserSettings.this, UserMain.class);
                                UserSettings.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserSettings.this);
                                builder.setMessage("Izmaiņas nav veiktas.")
                                        .setNegativeButton("Mēģināt vēlreiz", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EditUser editUserRequest = new EditUser(user_id, name, username, password, responseListener);

                RequestQueue queue = Volley.newRequestQueue(UserSettings.this);
                queue.add(editUserRequest);
            }
        });


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
