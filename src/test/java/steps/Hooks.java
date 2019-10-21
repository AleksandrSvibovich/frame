package steps;

import com.google.inject.Inject;
import logger.ProjectLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aleksandr_Svibovich on 9/16/2019.
 */
public class Hooks {
    private WebDriver driver;

    @Inject
    protected ProjectLog logger;

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.reportsFolder = "target/test-result/reports";
        Configuration.startMaximized = true;

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            driver = WebDriverRunner.getWebDriver();
        } catch (IllegalStateException ex) {
            logger.warn("no web driver to close");
        }

        try {
            if (scenario.isFailed()) {
                byte[] pic = takeScreenshot();
                ByteArrayInputStream bis = new ByteArrayInputStream(pic);
                BufferedImage bufferedImage = ImageIO.read(bis);
                ImageIO.write(bufferedImage, "png", new File(scenario.getName() + "-1.png"));
            }
        } catch (NullPointerException ex) {
            logger.error("image is not created");
        } catch (IOException e) {
            logger.error(e.toString());
        } finally {
            if (driver != null) {
                WebDriverRunner.closeWebDriver();
            }
        }
    }

    @Attachment(type = "image/png")
    public byte[] takeScreenshot() {
        System.out.println("taking screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
