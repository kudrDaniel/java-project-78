package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void appTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        App actual0 = new App();
        App.main(new String[]{});
        String actual1 = outputStream.toString();

        String expected1 = "Project initialized successful";

        assertThat(actual0).isNotNull();
        assertThat(actual1.trim()).isEqualTo(expected1.trim());
    }

    @Test
    public void validatorStringSchemaTest() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(Integer.parseInt("5"))).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        schema.contains(null).minLength(Integer.parseInt("5"));

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("what")).isFalse();
    }

    @Test
    public void validatorNumberSchemaTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue(); // true
        assertThat(schema.positive().isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("5")).isFalse(); // false
        assertThat(schema.isValid(Integer.parseInt("10"))).isTrue(); // true

        assertThat(schema.isValid(Integer.parseInt("-10"))).isFalse(); // false

        assertThat(schema.isValid(Integer.parseInt("0"))).isFalse(); // false

        schema.range(Integer.parseInt("5"), Integer.parseInt("10"));

        assertThat(schema.isValid(Integer.parseInt("5"))).isTrue(); // true
        assertThat(schema.isValid(Integer.parseInt("10"))).isTrue(); // true
        assertThat(schema.isValid(Integer.parseInt("4"))).isFalse(); // false
        assertThat(schema.isValid(Integer.parseInt("11"))).isFalse(); // false
    }
}
