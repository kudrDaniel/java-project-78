package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Integer sizeof;

    public MapSchema required() {
        super.setRequired(true);
        return this;
    }

    public MapSchema sizeof(Integer value) {
        this.sizeof = value;
        return this;
    }

    @Override
    public Boolean isValid(Object object) {
        if (object instanceof Map<?, ?> map) {
            Boolean isSizeofValid = true;
            if (this.sizeof != null) {
                isSizeofValid = map.size() == this.sizeof;
            }
            return isSizeofValid;
        } else {
            return super.isValid(object);
        }
    }
}
