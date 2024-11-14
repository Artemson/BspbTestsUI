package org.examplez;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TestNumberOne {
    static WebDriver driver;

    @Test
    public void cityChoose() {
        final By CITY_LIST_BUTTON = By.xpath("//div[@class='css-12ftcj6']/button[@id='menu-button-:R3qkqj6br6H1:']");
        final By CITY_KRASNODAR_OPTION = By.xpath("//div[@id='menu-list-:R3qkqj6br6H1:']/button[@id='menu-list-:R3qkqj6br6H1:-menuitem-:ra:']");
        final String EXPECTED_RESULT = "Краснодар";

        driver.get("https://www.bspb.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Ожидаем и кликаем на список городов
        WebElement cityListButton = wait.until(ExpectedConditions.elementToBeClickable(CITY_LIST_BUTTON));
        cityListButton.click();

        // Ожидаем и выбираем Краснодар
        WebElement cityKrasnodar = wait.until(ExpectedConditions.elementToBeClickable(CITY_KRASNODAR_OPTION));
        cityKrasnodar.click();

        // Ожидание изменения текста на выбранный город
        wait.until(ExpectedConditions.textToBePresentInElementLocated(CITY_LIST_BUTTON, EXPECTED_RESULT));
        String newCity = driver.findElement(CITY_LIST_BUTTON).getText();

        Assert.assertEquals(EXPECTED_RESULT, newCity);
    }

    @Test
    public void credit() {
        final String EXPECTED_RESULT = "105 746 ₽";

        driver.get("https://www.bspb.ru");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tabCredit = driver.findElement(By.xpath("//button[@id='tabs-:Rbql6br6:--tab-1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(tabCredit).click().perform();
        WebElement amountCredit = driver.findElement(By.xpath("//div[@class='css-te1w57']/div[@class='css-n26t5f'][1]/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a']"));
        WebElement timeCredit = driver.findElement(By.xpath("//div[@class='css-te1w57']/div[@class='css-n26t5f'][2]/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a']"));


        amountCredit.sendKeys(Keys.CONTROL + "a");
        amountCredit.sendKeys(Keys.DELETE, "2_000_000", Keys.ENTER);

        timeCredit.sendKeys(Keys.CONTROL + "a");
        timeCredit.sendKeys(Keys.DELETE, "27", Keys.ENTER);

        WebElement res = driver.findElement(By.xpath("//div[@class='css-j7qwjs'][1]/div[@class='css-1exebyi']/h2[@class='css-1bw6t7s']"));
        wait.until(ExpectedConditions.textToBePresentInElement(res, EXPECTED_RESULT));
        Assert.assertEquals(EXPECTED_RESULT, res.getText());
    }


    @Test
    public void deposit(){
        final String EXPECTED_WAGE = "5 895 730 ₽";
        final String EXPECTED_RATE = "18 %";

        driver.get("https://www.bspb.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        WebElement tabDeposit = driver.findElement(By.xpath("//button[@id='tabs-:Rbql6br6:--tab-0']"));
        actions.moveToElement(tabDeposit).click().perform();
        WebElement amountDeposit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-n26t5f']/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a']")));
        amountDeposit.sendKeys(Keys.CONTROL + "A");
        amountDeposit.sendKeys(Keys.DELETE);
        amountDeposit.sendKeys("16_310_000");
        amountDeposit.sendKeys(Keys.ENTER);
        WebElement periodDeposit = driver.findElement(By.xpath("//li[@class='chakra-wrap__listitem css-1yp4ln'][5]/label[@class='chakra-radio css-1cqh9jq']/span[@class='chakra-radio__label css-1inbprs']"));
        wait.until(ExpectedConditions.visibilityOf(periodDeposit));
        actions.moveToElement(periodDeposit).click().perform();
        WebElement wageDepositElement = driver.findElement(By.xpath("//div[@class='css-7ixs27']/div[@class='css-1ac3fhy'][1]/h2[@class='css-1bw6t7s']"));
        WebElement rateDepositElement = driver.findElement(By.xpath("//div[@class='css-7ixs27']/div[@class='css-1ac3fhy'][2]/h2[@class='css-1bw6t7s']"));

        wait.until(ExpectedConditions.textToBePresentInElement(wageDepositElement, EXPECTED_WAGE));
        wait.until(ExpectedConditions.textToBePresentInElement(rateDepositElement, EXPECTED_RATE));

        String wageDeposit = wageDepositElement.getText();
        String rateDeposit = rateDepositElement.getText();

        Assert.assertEquals(EXPECTED_WAGE, wageDeposit);
        Assert.assertEquals(EXPECTED_RATE, rateDeposit);
    }

    @Test
    public void mortgage() throws InterruptedException {
        final String EXPECTED_MONTHLY_PAYMENT_OF_MORTGAGE = "42 600 ₽";
        final String EXPECTED_RATE_OF_MORTGAGE = "6.00%";
        final By MONTHLY_PAYMENT_OF_MORTGAGE_LOCATOR = By.xpath("//div[@class='css-xr5uwr']/div[@class='css-1ac3fhy'][1]/h2[@class='css-1bw6t7s']");
        final By RATE_OF_MORTGAGE_LOCATOR = By.xpath("//div[@class='css-xr5uwr']/div[@class='css-1ac3fhy'][2]/h2[@class='css-1bw6t7s']");

        driver.get("https://www.bspb.ru");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tabMortgage = driver.findElement(By.xpath("//button[@id='tabs-:Rbql6br6:--tab-2']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(tabMortgage).click().perform();

        WebElement priceOfRealty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-te1w57']/div[@class='css-n26t5f'][1]/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a'][preceding-sibling::p[text()='Стоимость недвижимости']]\n")));
        priceOfRealty.sendKeys(Keys.CONTROL + "a");
        priceOfRealty.sendKeys(Keys.DELETE, "7_985_000", Keys.ENTER);

        WebElement timeMortgage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-te1w57']/div[@class='css-n26t5f'][2]/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a'][preceding-sibling::p[text()='Срок\']]\n")));
        timeMortgage.sendKeys(Keys.CONTROL + "a");
        timeMortgage.sendKeys(Keys.DELETE, "201", Keys.ENTER);

        WebElement initialPayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-te1w57']/div[@class='css-n26t5f'][3]/div[@class='css-pxyno3']/input[@class='chakra-input css-sacd3a'][preceding-sibling::p[text()='Первоначальный взнос']]\n")));
        initialPayment.sendKeys(Keys.CONTROL + "a");
        initialPayment.sendKeys(Keys.DELETE, "2_607_142", Keys.ENTER);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(MONTHLY_PAYMENT_OF_MORTGAGE_LOCATOR, EXPECTED_MONTHLY_PAYMENT_OF_MORTGAGE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(RATE_OF_MORTGAGE_LOCATOR, EXPECTED_RATE_OF_MORTGAGE));

        WebElement monthlyPaymentOfMortgage = driver.findElement(MONTHLY_PAYMENT_OF_MORTGAGE_LOCATOR);
        WebElement ratePaymentOfMortgage = driver.findElement(RATE_OF_MORTGAGE_LOCATOR);

        Assert.assertEquals(EXPECTED_MONTHLY_PAYMENT_OF_MORTGAGE, monthlyPaymentOfMortgage.getText());
        Assert.assertEquals(EXPECTED_RATE_OF_MORTGAGE, ratePaymentOfMortgage.getText());
    }

    @Ignore
    @Test
    public void loginNewBank() throws InterruptedException {
        driver.get("https://www.bspb.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='chakra-container css-11vl7h3']/div[@class='css-69i1ev']/button[@id='popover-trigger-:R6krqj6br6H1:']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-147gadc']/div[@class='css-70qvj9'][2]/a[@class='chakra-link css-9l4u1g']"))).click();
        WebElement phoneInput = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div/form/div/div[1]/div[1]/div/div"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div/form/div/div[1]/div[1]/div/div"));
        phoneInput.sendKeys("12345");
        passwordInput.sendKeys("67890");
    }

    @After
    public void quit() {
        driver.quit();
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}



