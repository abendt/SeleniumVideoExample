package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class TestContainersExample {

    private static File videoDirectory = new File("target/videos");

    @Container
    BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
            .withRecordingMode(
                    BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
                    videoDirectory,
                    VncRecordingContainer.VncRecordingFormat.MP4
            );

    @BeforeAll
    static void beforeAll() {
        videoDirectory.mkdirs();
    }

    @Test
    void canRecordVideo() {
        WebDriver driver = chrome.getWebDriver();

        driver.get("https://www.baeldung.com/");
        Assertions.assertEquals("foo", driver.getTitle());

        driver.findElement(By.linkText("NEWSLETTER")).click();
    }
}
