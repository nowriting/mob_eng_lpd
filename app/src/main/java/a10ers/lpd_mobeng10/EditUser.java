package a10ers.lpd_mobeng10;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eliza on 27-Apr-17.
 */

public class EditUser extends StringRequest {

    private static final String EDIT_USER_REQUEST_URL = "https://show-me-the-money.000webhostapp.com/android_app/lpd_registerEdit.php";
    private Map<String, String> params;

    public EditUser(String user_id, String name, String username, String password, Response.Listener<String> listener){

        super(Method.POST, EDIT_USER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
