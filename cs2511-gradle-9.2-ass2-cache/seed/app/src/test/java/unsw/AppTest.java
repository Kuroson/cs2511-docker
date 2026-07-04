package unsw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void payloadContainsExpectedFields() throws JSONException {
        JSONObject payload = new JSONObject(App.buildPayload());
        assertEquals("seed", payload.getString("app"));
        assertTrue(payload.getBoolean("ok"));
    }

    @Test
    public void dependenciesAreReachable() {
        assertTrue(App.touchDependencies() >= 0);
    }
}
