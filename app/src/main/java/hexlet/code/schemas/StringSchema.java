package hexlet.code.schemas;

public final class StringSchema {
    private Boolean isRequired;

    private Integer minLength;

    private String pattern;

    public StringSchema required() {
        this.isRequired = true;
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

    public Boolean isValid(Object object) {
        if (object instanceof String) {
            String string = object.toString();
            Boolean isRequiredValid = true;
            if (this.isRequired != null) {
                isRequiredValid = this.isRequired && !string.isEmpty();
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
            return object == null && this.isRequired == null;
        }
    }
}
