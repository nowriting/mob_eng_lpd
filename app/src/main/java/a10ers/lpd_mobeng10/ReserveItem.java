package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReserveItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_item);

        final Button bBack = (Button) findViewById(R.id.bBacktoInventoryMenu2);

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
