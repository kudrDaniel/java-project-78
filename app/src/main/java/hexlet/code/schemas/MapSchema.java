package hexlet.code.schemas;

import lombok.NonNull;

import java.util.Map;
import java.util.stream.Collectors;

public final class MapSchema extends BaseSchema {
    private Integer sizeof;

    private Map<String, BaseSchema> shape;

    public MapSchema required() {
        super.setRequired(true);
        return this;
    }

    public MapSchema sizeof(Integer value) {
        this.sizeof = value;
        return this;
    }

    public MapSchema shape(@NonNull Map<String, BaseSchema> schemas) {
        this.shape = schemas;
        return this;
    }

    @Override
    public Boolean isValid(Object object) {
        if (object instanceof Map<?, ?> map) {
            Boolean isSizeofValid = true;
            if (this.sizeof != null) {
                isSizeofValid = map.size() == this.sizeof;
            }
            Boolean isShapeValid = true;
            if (this.shape != null && !this.shape.isEmpty() && !map.isEmpty()) {
                isShapeValid = isShapeValid(map);
            }
            return isSizeofValid && isShapeValid;
        } else {
            return super.isValid(object);
        }
    }

    private Boolean isShapeValid(Map<?, ?> check) {
        return check.entrySet().stream()
                .filter(entry -> {
                    if (entry.getKey() instanceof String key) {
                        BaseSchema schema = this.shape.getOrDefault(key, new BaseSchema() { });
                        return schema.isValid(entry.getValue());
                    }
                    return false;
                })
                .collect(Collectors.toSet()).size() == check.size();
    }
}
