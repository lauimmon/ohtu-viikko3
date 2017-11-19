package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/ChromeDriver/chromedriver.exe"); 
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        luoUusiKayttajaTunnusJaKirjauduUlos(driver);
    }

    private static void testiKirjauduOnnistuneesti(WebDriver driver) {
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
    }
    
    private static void testiYritaKirjautuaVaarallaSalasanalla(WebDriver driver) {
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
    }
    
    private static void testiYritaKirjautuaVaarallaKayttajatunnuksella(WebDriver driver) {
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("jukkaaa666");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        
        
        sleep(3);
        
        driver.quit();
    }
    
    private static void luoUusiKayttajaTunnusJaKirjauduUlos(WebDriver driver) {
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random r = new Random();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("jukka" + r.nextInt(1000000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkuj");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkuj");
        element = driver.findElement(By.name("signup"));
        element.submit();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
