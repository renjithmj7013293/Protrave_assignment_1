import org.junit.runner.RunWith;
import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"Steps" , "hooks"},
        tags = "@BDD_Sauce1",
        plugin = {"pretty", "html:target/cucumber-report.html"}
        )

public class CucumberRunner {
}
