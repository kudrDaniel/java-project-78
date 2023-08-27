package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void appTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        App.main(new String[]{});
        String actual = outputStream.toString();

        String expected = "Project initialized successful";

        assertThat(actual.trim()).isEqualTo(expected.trim());
    }
}
