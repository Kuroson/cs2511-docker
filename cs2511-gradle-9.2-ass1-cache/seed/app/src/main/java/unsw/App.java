package unsw;

import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.json.JSONObject;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Trivial seed application. It touches every runtime dependency so that the
 * full Gradle dependency graph is resolved (and therefore cached) when the
 * project is built. It is not meant to do anything useful.
 */
public final class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private App() {
    }

    /**
     * Builds a small JSON payload using org.json and Gson.
     *
     * @return the serialized payload
     */
    public static String buildPayload() {
        JSONObject obj = new JSONObject();
        obj.put("app", "seed");
        obj.put("ok", true);

        Gson gson = new Gson();
        return gson.toJson(obj.toMap());
    }

    /**
     * References the remaining dependencies so their classes are loaded at
     * runtime as well as resolved at compile time.
     *
     * @return an arbitrary count derived from the referenced types
     */
    public static int touchDependencies() {
        Reflections reflections = new Reflections("unsw", Scanners.SubTypes);
        Set<Class<?>> types = reflections.getSubTypesOf(Object.class);

        CSVFormat csvFormat = CSVFormat.DEFAULT;
        Class<?> sparkClass = spark.Spark.class;

        return types.size() + csvFormat.toString().length() + sparkClass.getName().length();
    }

    /**
     * Entry point. Logs the payload and the dependency reference count.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        LOG.info("payload: {}", buildPayload());
        LOG.info("touched {} dependency references", touchDependencies());
    }
}
