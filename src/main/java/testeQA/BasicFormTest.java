package testeQA;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BasicFormTest {

    private static String formUrl, usernameBoxName, passwordBoxName, commentsBoxName, checkbox1Xpath, checkbox2Xpath,
            multipleSelectName, dropdownName, radio1Xpath, buttonSubmitXpath, usernameValue, passwordValue, commentValue,
            multipleSelectValue1, multipleSelectValue4, dropDownValue1, usernameResultId, passwordResultId,
            commentsResultId, checkboxValue1, checkboxValue2, checkboxValue3, checkboxResultId1, checkboxResultId2,
            checkboxResultId3, radio1Value, radio1ResultId, multipleSelect1ResultId, dropDownResultId;

    @BeforeClass
    public static void setup() {
        formUrl = "https://testpages.herokuapp.com/styled/basic-html-form-test.html";
        usernameBoxName = "username";
        passwordBoxName = "password";
        commentsBoxName = "comments";
        checkbox1Xpath = "//input[@value='cb1']";
        checkbox2Xpath = "//input[@value='cb2']";
        checkboxValue1 = "cb1";
        checkboxValue2 = "cb2";
        checkboxValue3 = "cb3";
        multipleSelectName = "multipleselect[]";
        dropdownName = "dropdown";
        radio1Xpath = "//input[@value='rd1']";
        buttonSubmitXpath = "//input[@value='submit']";
        usernameValue = "QA Name";
        passwordValue = "QA Senha";
        commentValue = "Comentario";
        multipleSelectValue1 = "ms1";
        multipleSelectValue4 = "ms4";
        dropDownValue1 = "dd1";
        usernameResultId = "_valueusername";
        passwordResultId = "_valuepassword";
        commentsResultId = "_valuecomments";
        checkboxResultId1 = "_valuecheckboxes0";
        checkboxResultId2 = "_valuecheckboxes1";
        checkboxResultId3 = "_valuecheckboxes2";
        radio1Value = "rd1";
        radio1ResultId = "_valueradioval";
        multipleSelect1ResultId = "_valuemultipleselect0";
        dropDownResultId = "_valuedropdown";
    }


    @Test
    public void basicForm() {

        WebDriverManager.chromiumdriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(formUrl);

        WebElement userNameBox = driver.findElement(By.name(usernameBoxName));
        WebElement passwordBox = driver.findElement(By.name(passwordBoxName));
        WebElement commentsBox = driver.findElement(By.name(commentsBoxName));

        WebElement checkbox1 = driver.findElement(By.xpath(checkbox1Xpath));
        WebElement checkbox2 = driver.findElement(By.xpath(checkbox2Xpath));

        WebElement multipleSelect = driver.findElement(By.name(multipleSelectName));
        Select selectMultipleSelect = new Select(multipleSelect);

        WebElement dropdown = driver.findElement(By.name(dropdownName));
        Select selectDropdown = new Select(dropdown);

        WebElement radio1 = driver.findElement(By.xpath(radio1Xpath));

        WebElement buttonSubmit = driver.findElement(By.xpath(buttonSubmitXpath));

        userNameBox.sendKeys(usernameValue);
        passwordBox.sendKeys(passwordValue);
        commentsBox.clear();
        commentsBox.sendKeys(commentValue);
        checkbox1.click();
        checkbox2.click();
        selectMultipleSelect.selectByValue(multipleSelectValue1);
        selectMultipleSelect.deselectByValue(multipleSelectValue4);
        selectDropdown.selectByValue(dropDownValue1);
        radio1.click();

        buttonSubmit.click();

        Assert.assertEquals(usernameValue, driver.findElement(By.id(usernameResultId)).getText());
        Assert.assertEquals(passwordValue, driver.findElement(By.id(passwordResultId)).getText());
        Assert.assertEquals(commentValue, driver.findElement(By.id(commentsResultId)).getText());
        Assert.assertEquals(checkboxValue1, driver.findElement(By.id(checkboxResultId1)).getText());
        Assert.assertEquals(checkboxValue2, driver.findElement(By.id(checkboxResultId2)).getText());
        Assert.assertEquals(checkboxValue3, driver.findElement(By.id(checkboxResultId3)).getText());
        Assert.assertEquals(radio1Value, driver.findElement(By.id(radio1ResultId)).getText());
        Assert.assertEquals(multipleSelectValue1, driver.findElement(By.id(multipleSelect1ResultId)).getText());
        Assert.assertEquals(dropDownValue1, driver.findElement(By.id(dropDownResultId)).getText());


        driver.quit();
    }

    @Test
    public void basicFormEmpty() {

        WebDriverManager.chromiumdriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(formUrl);

        WebElement commentsBox = driver.findElement(By.name(commentsBoxName));

        WebElement multipleSelect = driver.findElement(By.name(multipleSelectName));
        Select selectMultipleSelect = new Select(multipleSelect);

        WebElement dropdown = driver.findElement(By.name(dropdownName));
        Select selectDropdown = new Select(dropdown);

        WebElement radio1 = driver.findElement(By.xpath(radio1Xpath));

        WebElement buttonSubmit = driver.findElement(By.xpath(buttonSubmitXpath));

        commentsBox.clear();
        selectMultipleSelect.selectByValue(multipleSelectValue1);
        selectMultipleSelect.deselectByValue(multipleSelectValue4);
        selectDropdown.selectByValue(dropDownValue1);
        radio1.click();

        buttonSubmit.click();


        Boolean usernameResultExists = !driver.findElements(By.id(usernameResultId)).isEmpty();
        Boolean passwordResultExists = !driver.findElements(By.id(passwordResultId)).isEmpty();
        Boolean commentsResultExists = !driver.findElements(By.id(commentsResultId)).isEmpty();


        Assert.assertFalse(usernameResultExists);
        Assert.assertFalse(passwordResultExists);
        Assert.assertFalse(commentsResultExists);

        driver.quit();
    }
}
