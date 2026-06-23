package unsw;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class AppTest {
    @Test
    public void payloadContainsExpectedFields() throws JSONException {
        String payload = App.buildPayload();
        JSONAssert.assertEquals("{\"app\":\"seed\",\"ok\":true}", payload, false);
    }

    @Test
    public void dependenciesAreReachable() {
        assertTrue(App.touchDependencies() >= 0);
    }
}
