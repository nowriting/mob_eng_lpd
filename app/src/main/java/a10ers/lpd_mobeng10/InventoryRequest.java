package a10ers.lpd_mobeng10;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eliza on 25-Apr-17.
 */

public class InventoryRequest extends StringRequest {
    private static final String INVENTORY_REQUEST_URL = "https://show-me-the-money.000webhostapp.com/android_app/getinventory.php";
    private Map<String, String> params;

    public InventoryRequest(String username, String password, Response.Listener<String> listener){
        super(Request.Method.POST, INVENTORY_REQUEST_URL, listener, null);
        params = new HashMap<>();
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
