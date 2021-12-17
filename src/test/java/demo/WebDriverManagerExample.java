package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

public class WebDriverManagerExample {

    private static File videoDirectory = new File("target/videos");

    private WebDriverManager webDriverManager;

    @BeforeEach
    void setUp() {
        webDriverManager = WebDriverManager.chromedriver()
                .browserInDocker()
                .enableVnc()
                .recordingOutput(videoDirectory.toPath())
                .enableRecording();
    }

    @BeforeAll
    static void beforeAll() {
        videoDirectory.mkdirs();
    }

    @Test
    void canRecordVideo() {
        WebDriver driver = webDriverManager.create();

        driver.get("https://www.baeldung.com/");
        Assertions.assertEquals("foo", driver.getTitle());

        driver.findElement(By.linkText("NEWSLETTER")).click();
    }
}
