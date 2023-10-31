package SauceDemo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/SauceDemo/features",
        glue = "SauceDemo.stepDef",
        plugin = {"html:target/HTML_report.html"}
)
public class runner {
}
