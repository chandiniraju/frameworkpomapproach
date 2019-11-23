package com.mavenit.pom.practise.page_elements;


import com.mavenit.pom.practise.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.mavenit.pom.practise.testutils.RandomNumberHelper;

import java.util.List;

import static junit.framework.TestCase.fail;

public class HomePage extends Hooks {


   // public String searchItem;
 public void search(String item)
 {
     driver.findElement(By.id("search")).clear();
     driver.findElement(By.id("search")).clear();
     //searchItem=item;
     System.out.println(item);
     enterSearchItem(item);
     clickSearch();

 }
public void enterSearchItem(String item)
{
    driver.findElement(By.id("search")).sendKeys(item);

}
public void clickSearch()
{
    driver.findElement(By.xpath("//button[@type='submit']")).click();
}
public String selectDepartmentFromSuggestions(String item)
{
    String selected=selectFromSuggestions(item,By.xpath("//div[@class='dc-search-suggestions__suggestion dc-search-suggestions__suggestion--term']"));
    return selected;
}
public String selectProductsFromSuggestions(String item)
{
    String selected=selectFromSuggestions(item,By.xpath("//div[@class='dc-search-suggestions__suggestion dc-search-suggestions__suggestion--sayt']"));
     return selected;
}

public String  selectFromSuggestions(String item,By by) {
    List<WebElement> suggestionElements = driver.findElements(by);
    int listSize = suggestionElements.size();
    if (listSize == 0) {
        fail("no suggestions");
    }
    int randomIndex = new RandomNumberHelper().generateRandomNumber(listSize);
    WebElement selectedElement = suggestionElements.get(randomIndex);

    String selectedSuggestion = selectedElement.getText();
    System.out.println("selected suggestion is   " + selectedSuggestion);
    selectedElement.click();
    return selectedSuggestion;

}
}

