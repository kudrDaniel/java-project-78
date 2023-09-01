package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks = new HashMap<>();

    protected final void addCheck(String key, Predicate<Object> predicate) {
        this.checks.put(key, predicate);
    }

    public final Boolean isValid(Object obj) {
        return (obj == null && !this.checks.containsKey("required"))
                || this.checks.values().stream()
                .allMatch(predicate -> predicate.test(obj));
    }
}
