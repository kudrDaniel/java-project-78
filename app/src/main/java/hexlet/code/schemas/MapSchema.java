package hexlet.code.schemas;

import lombok.NonNull;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        super.addCheck("required", obj -> obj instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(Integer sizeof) {
        super.addCheck("sizeof", obj -> obj instanceof Map<?, ?> map
                && map.size() == sizeof);
        return this;
    }

    public MapSchema shape(@NonNull Map<String, BaseSchema> shape) {
        super.addCheck("shape", obj -> obj instanceof Map<?, ?> map
                && isShapeValid(map, shape));
        return this;
    }

    private Boolean isShapeValid(Map<?, ?> check, Map<String, BaseSchema> shape) {
        return !check.isEmpty() && !shape.isEmpty() && check.entrySet().stream()
                .allMatch(entry -> {
                    if (entry.getKey() instanceof String key) {
                        BaseSchema schema = shape.getOrDefault(key, new BaseSchema() { });
                        return schema.isValid(entry.getValue());
                    }
                    return false;
                });
    }
}
