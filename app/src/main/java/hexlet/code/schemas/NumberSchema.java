package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        super.addCheck("required", obj -> obj instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        super.addCheck("positive", obj -> obj instanceof Integer number
                && number > 0);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        super.addCheck("range", obj -> obj instanceof Integer number
                && number >= from && number <= to);
        return this;
    }
}
