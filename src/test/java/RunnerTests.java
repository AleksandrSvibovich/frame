import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Created by Aleksandr_Svibovich on 9/16/2019.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"progress",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber-reports/cucumber.json"
        },
        glue = {"steps","java","services"}
)


public class RunnerTests {

}
