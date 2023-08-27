package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private Boolean isPositive;

    private Integer rangeFrom;

    private Integer rangeTo;

    public NumberSchema required() {
        super.setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        this.rangeFrom = from;
        this.rangeTo = to;
        return this;
    }

    @Override
    public Boolean isValid(Object object) {
        if (object instanceof Integer number) {
            Boolean isPositiveValid = true;
            if (this.isPositive != null) {
                isPositiveValid = number > 0;
            }
            Boolean isRangeValid = true;
            if (this.rangeFrom != null && this.rangeTo != null) {
                isRangeValid = number >= this.rangeFrom && number <= this.rangeTo;
            }
            return isPositiveValid && isRangeValid;
        } else {
            return super.isValid(object);
        }
    }
}
