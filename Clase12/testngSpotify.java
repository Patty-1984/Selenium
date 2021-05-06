package Clase12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.*;

public class testngSpotify {

    public WebDriver driver;
    public static final String CORREO = "Es necesario que introduzcas tu correo electrónico.";
    public static final String CONFIRMARCORREO = "Es necesario que confirmes tu correo electrónico.";
    public static final String CONTRASEÑA = "Debes introducir una contraseña.";
    public static final String NOMBREPERFIL = "Introduce un nombre para tu perfil.";
    public static final String DIA = "Indica un día del mes válido.";
    public static final String MES = "Selecciona tu mes de nacimiento.";
    public static final String AÑO = "Indica un año válido.";
    public static final String SEXO = "Selecciona tu sexo.";
    public static final String ROBOT = "Confirma que no eres un robot.";

    @BeforeMethod
    public void setUp (){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); //"drivers/chromedriver.exe" dirección del archivo ejecutable
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
    }

    @Test (priority = 6)
    public void verifySpotifyTitle(){
        String titulo = driver.getTitle();
        Assert.assertEquals(titulo, "Escuchar es todo - Spotify");
    }

    @Test(priority = 5)
    public void verifySignupUrl(){
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/cl/signup/']")).click();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.spotify.com/cl/signup/");
    }

    @Test(priority = 4)
    public void  invalidEmailTest(){
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/cl/signup/']")).click();
        driver.findElement(By.id("email")).sendKeys("test.com");
        driver.findElement(By.id("confirm")).sendKeys("test.com");
        WebElement mensajeError = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico no es válido')]"));
        Assert.assertEquals(mensajeError.getText(),"Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com");
    }

    @Test(priority = 3)
    public void validateExistingEmail() throws InterruptedException{
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/cl/signup/']")).click();
        driver.findElement(By.id("email")).sendKeys("test@test.com");
        driver.findElement(By.id("confirm")).sendKeys("test@test.com");
        sleep(2000);
        WebElement mensajeError2 = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico ya está conectado a una cuenta. ')]"));
        Assert.assertEquals(mensajeError2.getText(),"Este correo electrónico ya está conectado a una cuenta. Inicia sesión.");
    }

    @Test(priority = 2)
    public void checkEqualEmailsError(){
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/cl/signup/']")).click();
        driver.findElement(By.id("email")).sendKeys("test999@test.com");
        driver.findElement(By.id("confirm")).sendKeys("hola@test.com");
        driver.findElement(By.id("password")).sendKeys("");
        //sleep(2000);
        WebElement mensajeError2 = driver.findElement(By.xpath("//*[contains(text(),'Las direcciones de correo electrónico no coinciden.')]"));
        Assert.assertEquals(mensajeError2.getText(),"Las direcciones de correo electrónico no coinciden.");
    }

    @Test(priority = 1)
    public void checkErrorMessages(){
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/cl/signup/']")).click();
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        WebElement mensajeCorreo = driver.findElement(By.xpath("//*[contains(text(),'Es necesario que introduzcas tu correo electrónico.')]"));
        Assert.assertEquals(mensajeCorreo.getText(),CORREO);
        WebElement mensajeConfirmar = driver.findElement(By.xpath("//*[contains(text(),'Es necesario que confirmes tu correo electrónico.')]"));
        Assert.assertEquals(mensajeConfirmar.getText(),CONFIRMARCORREO);
        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'Debes introducir una contraseña.')]"));
        Assert.assertEquals(error.getText(),CONTRASEÑA);
        error = driver.findElement(By.xpath("//*[contains(text(),'Introduce un nombre para tu perfil.')]"));
        Assert.assertEquals(error.getText(),NOMBREPERFIL);
        error = driver.findElement(By.xpath("//*[contains(text(),'Indica un día del mes válido.')]"));
        Assert.assertEquals(error.getText(),DIA);
        error = driver.findElement(By.xpath("//*[contains(text(),'Selecciona tu mes de nacimiento.')]"));
        Assert.assertEquals(error.getText(),MES);
        error = driver.findElement(By.xpath("//*[contains(text(),'Indica un año válido.')]"));
        Assert.assertEquals(error.getText(),AÑO);
        error = driver.findElement(By.xpath("//*[contains(text(),'Selecciona tu sexo.')]"));
        Assert.assertEquals(error.getText(),SEXO);
        error = driver.findElement(By.xpath("//*[contains(text(),'Confirma que no eres un robot.')]"));
        Assert.assertEquals(error.getText(),ROBOT);

    }

    @AfterMethod
    public void closed() throws InterruptedException {
        sleep(2000);
        driver.close();
    }



}
