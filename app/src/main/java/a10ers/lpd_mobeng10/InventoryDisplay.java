package a10ers.lpd_mobeng10;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static a10ers.lpd_mobeng10.R.id.parent;
import static a10ers.lpd_mobeng10.R.styleable.View;

import android.content.Intent;
import android.widget.Button;


public class InventoryDisplay extends AppCompatActivity {

    private static final String item_id = "item_id";
    private static final String item_name = "item_name";
    private static final String item_amount = "item_amount";
    private static final String item_place = "item_place";

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD ="address";
    private static final String TAG_AMOUNT  ="item_amount";
    private static final String TAG_PLACE = "item_place";

    JSONArray inventory = null;

    ArrayList<HashMap<String, String>> inventoryList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        final Button bBack = (Button) findViewById(R.id.bBacktoInventoryMenu);

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(InventoryDisplay.this, InventoryMenu.class);
                InventoryDisplay.this.startActivity(registerIntent);
            }
        });

        list = (ListView) findViewById(R.id.listView);
        inventoryList = new ArrayList<>();
        getData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = list.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"your text", Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            inventory = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<inventory.length();i++){
                JSONObject c = inventory.getJSONObject(i);
                String item_id = c.getString(TAG_ID);
                String item_name = c.getString(TAG_NAME);
                String item_amount = c.getString(TAG_AMOUNT);
                String item_place = c.getString(TAG_PLACE);

                HashMap<String,String> items = new HashMap<>();

                items.put(TAG_ID, item_id);
                items.put(TAG_NAME, item_name);
                items.put(TAG_AMOUNT, item_amount);
                items.put(TAG_PLACE, item_place);

                inventoryList.add(items);
            }

            ListAdapter adapter = new SimpleAdapter(
                    InventoryDisplay.this, inventoryList, R.layout.list_item,
                    new String[]{TAG_ID,TAG_NAME,TAG_AMOUNT, TAG_PLACE},
                    new int[]{R.id.id, R.id.name, R.id.amount, R.id.place}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("https://show-me-the-money.000webhostapp.com/android_app/getinventory.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{
                        if(inputStream != null)inputStream.close();
                    }
                    catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
