import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by Plong on 6/2/2016 AD.
 */
public class LoginTest {
    AndroidDriver driver;
    WebElement editTextUsername;
    WebElement editTextPassword;
    WebElement btnLogin;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testInvalidLogin() throws Exception{
        editTextUsername = driver.findElement(By.id("com.mvision.applogin:id/edit_username"));
        editTextUsername.sendKeys("root");
        editTextPassword = driver.findElement(By.id("com.mvision.applogin:id/edit_password"));
        editTextPassword.sendKeys("root123");

        btnLogin = driver.findElement(By.id("com.mvision.applogin:id/btn_login"));
        btnLogin.click();

        WebElement message = driver.findElement(By.id("android:id/message"));
        assertEquals("Invalid username or password.", message.getText().toString());
    }

    @Test
    public void testValidLogin() throws Exception{
        editTextUsername = driver.findElement(By.id("com.mvision.applogin:id/edit_username"));
        editTextUsername.sendKeys("root");
        editTextPassword = driver.findElement(By.id("com.mvision.applogin:id/edit_password"));
        editTextPassword.sendKeys("root");

        btnLogin = driver.findElement(By.id("com.mvision.applogin:id/btn_login"));
        btnLogin.click();

        assertEquals(".MainActivity", driver.currentActivity());
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }
}
