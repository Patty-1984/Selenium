package Clase12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testngSalesforce {
    private WebDriver driver;
    private static final String SALEFORCE_URL = "https://login.salesforce.com/";

    @BeforeMethod
    public void acceso (){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); //"drivers/chromedriver.exe" dirección del archivo ejecutable
        driver = new ChromeDriver();
        driver.get(SALEFORCE_URL);
    }

    @Test (priority = 1, enabled = false)
    public void validateSalesforceLogoTest(){
        WebElement tag = driver.findElement(By.id("logo"));
        System.out.println("TagName -->"+ tag.getTagName());
        System.out.println("Alt -->"+ tag.getAttribute("alt"));
    }

    @Test (priority = 4)
    public void RememberMeIsSelected(){
        WebElement elemento = driver.findElement(By.id("rememberUn"));
        elemento.click();
        Assert.assertTrue(elemento.isSelected());
    }

    @Test (priority = 2)
    public void FooterIsValid(){
        WebElement footer = driver.findElement(By.id("footer"));
        Assert.assertTrue(footer.getText().contains("Reservados todos los derechos."));
    }


    @AfterMethod
    public void close (){
        driver.close();
    }


}
