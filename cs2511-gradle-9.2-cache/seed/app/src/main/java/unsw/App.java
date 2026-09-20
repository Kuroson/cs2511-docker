package unsw;

import org.apache.commons.csv.CSVFormat;
import org.json.JSONObject;

/**
 * Trivial seed application. It touches every runtime dependency so that the
 * full Gradle dependency graph is resolved (and therefore cached) when the
 * project is built. It is not meant to do anything useful.
 */
public final class App {
    private App() {
    }

    /**
     * Builds a small JSON payload using org.json.
     *
     * @return the serialized payload
     */
    public static String buildPayload() {
        JSONObject obj = new JSONObject();
        obj.put("app", "seed");
        obj.put("ok", true);
        return obj.toString();
    }

    /**
     * References the remaining dependencies so their classes are loaded at
     * runtime as well as resolved at compile time.
     *
     * @return an arbitrary count derived from the referenced types
     */
    public static int touchDependencies() {
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        return csvFormat.toString().length();
    }

    /**
     * Entry point. Prints the payload and the dependency reference count.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        System.out.println("payload: " + buildPayload());
        System.out.println("touched " + touchDependencies() + " dependency references");
    }
}
