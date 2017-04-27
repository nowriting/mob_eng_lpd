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

public class ReserveItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_item);

        final Button bBack = (Button) findViewById(R.id.bBacktoInventoryMenu2);
        final EditText etName = (EditText) findViewById(R.id.etitemName);
        final EditText etUsername = (EditText) findViewById(R.id.etitemAmount);
        final EditText etPassword = (EditText) findViewById(R.id.etitemPlace);
        final Button bSave = (Button) findViewById(R.id.bSaveItem);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Intent intent = new Intent(ReserveItem.this, InventoryDisplay.class);
                                ReserveItem.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ReserveItem.this);
                                builder.setMessage("Lietas ievietošana neveiksmīga.")
                                        .setNegativeButton("Mēģināt vēlreiz", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AddItemRequest addItemRequest = new AddItemRequest(name, username, password, responseListener);

                RequestQueue queue = Volley.newRequestQueue(ReserveItem.this);
                queue.add(addItemRequest);
            }
        });


        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(ReserveItem.this, InventoryMenu.class);
                ReserveItem.this.startActivity(registerIntent);
            }
        });
    }
}
