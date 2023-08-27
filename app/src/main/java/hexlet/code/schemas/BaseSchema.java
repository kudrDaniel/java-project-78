package hexlet.code.schemas;

public abstract class BaseSchema {
    private Boolean isRequired;

    public final Boolean getRequired() {
        return isRequired;
    }

    public final void setRequired(Boolean required) {
        isRequired = required;
    }

    /**
     * @param object object to check
     * @return return true if object is null and required flag does not touch
     */
    protected Boolean isValid(Object object) {
        return object == null && this.isRequired == null;
    }
}
