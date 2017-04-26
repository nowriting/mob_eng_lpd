package a10ers.lpd_mobeng10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InventoryMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);

        final Button bBack = (Button) findViewById(R.id.bBacktoUserMain);
        final Button bInventoryAll = (Button) findViewById(R.id.bInventoryAll);
        final Button bInventoryReserved = (Button) findViewById(R.id.bInventoryReserved);
        final TextView tvPrompt = (TextView) findViewById(R.id.tvInvenoryMenu);

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(InventoryMenu.this, UserMain.class);
                InventoryMenu.this.startActivity(registerIntent);
            }
        });



        String inventoryallname;
        inventoryallname = "Visa inventorija";
        bInventoryAll.setText(inventoryallname);

        bInventoryAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(InventoryMenu.this, InventoryDisplay.class);
                InventoryMenu.this.startActivity(registerIntent);
            }
        });


    }
}
