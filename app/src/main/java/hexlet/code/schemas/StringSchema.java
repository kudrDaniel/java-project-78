package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private Integer minLength;

    private String pattern;

    public StringSchema required() {
        super.setRequired(true);
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.pattern = substring;
        return this;
    }

    @Override
    public Boolean isValid(Object object) {
        if (object instanceof String string) {
            Boolean isRequiredValid = true;
            if (super.getRequired() != null) {
                isRequiredValid = super.getRequired() && !string.isEmpty();
            }
            Boolean isMinLengthValid = true;
            if (this.minLength != null) {
                isMinLengthValid = string.length() >= this.minLength;
            }
            Boolean isSubstringValid = true;
            if (this.pattern != null) {
                isSubstringValid = string.contains(this.pattern);
            }
            return isRequiredValid && isMinLengthValid && isSubstringValid;
        } else {
            return super.isValid(object);
        }
    }
}
