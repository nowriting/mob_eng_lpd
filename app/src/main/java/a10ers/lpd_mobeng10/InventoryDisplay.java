package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InventoryDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        final Button bBack = (Button) findViewById(R.id.bBacktoInventoryMenu);
        final Button bShowInventory = (Button) findViewById(R.id.bShowAllInventoryList);

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(InventoryDisplay.this, InventoryMenu.class);
                InventoryDisplay.this.startActivity(registerIntent);
            }
        });
    }
}
