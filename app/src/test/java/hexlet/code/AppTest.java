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
        App actual0 = new App();
        App.main(new String[]{});
        String actual1 = outputStream.toString();

        String expected1 = "Project initialized successful";

        assertThat(actual0).isNotNull();
        assertThat(actual1.trim()).isEqualTo(expected1.trim());
    }
}
