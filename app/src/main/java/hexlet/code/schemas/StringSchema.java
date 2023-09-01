package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        super.addCheck("required", (obj) -> obj instanceof String string
                && !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addCheck("minLength", (obj) -> obj instanceof String string
                && string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        super.addCheck("contains", (obj) -> obj instanceof String string
                && (substring == null || string.contains(substring)));
        return this;
    }
}
