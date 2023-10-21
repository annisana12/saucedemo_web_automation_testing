package helper;

import org.openqa.selenium.WebDriver;

// This class allows you to store and retrieve shared data between step definition classes
public class ScenarioContext {
    // Use a ThreadLocal to ensure each scenario has its own context
    private static ThreadLocal<ScenarioContext> scenarioContext = ThreadLocal.withInitial(ScenarioContext::new);
    private WebDriver driver;

    public static ScenarioContext get() {
        return scenarioContext.get();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
