


//to check branch in git


package com.mavenit.pom.practise;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;
import com.mavenit.pom.practise.page_elements.BasketPage;
import com.mavenit.pom.practise.page_elements.HomePage;
import com.mavenit.pom.practise.page_elements.ProductDescriptionPage;
import com.mavenit.pom.practise.page_elements.ResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegressionSuite extends Hooks {

 private HomePage homePage =new HomePage();
 private ResultsPage resultsPage=new ResultsPage();
 private ProductDescriptionPage productDescriptionPage=new ProductDescriptionPage();
 private BasketPage basketPage=new BasketPage();

    @Test
    public void searchTest()
    {
     String searchItem="Laptops";                                            //search for a item
     //driver.findElement(By.id("search")).clear();
     //driver.findElement(By.id("search")).sendKeys("searchItem");
     //driver.findElement(By.xpath("//button[@type='submit']")).click();

        homePage.search(searchItem);
        String actualTitle=resultsPage.getPageTitle();
        String actualThumbNail=resultsPage.getThumbNail();
        assertThat(actualThumbNail,is(equalToIgnoringCase(searchItem)));
        assertThat(actualTitle,is(equalToIgnoringCase(searchItem)));

    }

    @Test
    public void suggestedDepSearchTest()                                 //search from department suggestions
    {
       String searchItem="cable";
       homePage.enterSearchItem(searchItem);
       String actual=homePage.selectDepartmentFromSuggestions(searchItem);
       System.out.println("actual is   " +actual);
       //String actual=resultsPage.getThumbNail();
        assertThat(actual,containsString(searchItem));

    }
    @Test
    public void suggestedProductSearchTest()                             //search from product suggestions
    {
       String searchItem="Cable";
       homePage.enterSearchItem(searchItem);
       String actual=homePage.selectProductsFromSuggestions(searchItem);
       //String actual=resultsPage.getPageTitle();
       System.out.println("actual is  " +actual);
       assertThat(actual,containsString(searchItem));
    }

    @Test
   public void addProductToBasketTest()
    {
       homePage.search("usb flash drives");
       String expected= resultsPage.selectAnyProduct();
       System.out.println("expected is  " +expected);
       productDescriptionPage.addProductToBasket();

       String actual=basketPage.getProductsInBasket();
       System.out.println("actual is   " +actual);
       assertThat(actual,is(equalToIgnoringCase(expected)));
    }
    @Test
    public void filterPriceTest()
    {

        String searchTerm="laptops";   //searchTerm="kettle"
        homePage.search(searchTerm);

        //homePage.clickSearch();
        String priceRange="£299.00 - £499.00";               //with pound symbol we cannot ,so we are replacing with nothing
        resultsPage.selectPrice(priceRange);                    //and splitteing string on basis of - which reurns string array
        //we are coverting as string list
        List<String> expectedList = Arrays.asList(priceRange.replace("£", "").split("-"));
        double min=Double.valueOf(expectedList.get(0));
        double max=Double.valueOf(expectedList.get(1));
        List<Double>actualPriceList  = resultsPage.getAllProductsPrices();                 //we should not get products <299.00 and >499.00
        //for that reason we get all the prices of the search

        //assertThat(actualPriceList,both(everyItem(is(greaterThanOrEqualTo(min)))).and(everyItem(is(lessThanOrEqualTo(max)))));
        assertThat(actualPriceList,everyItem(is(greaterThanOrEqualTo(min))));
        assertThat(actualPriceList,everyItem(is(lessThanOrEqualTo(max))));


    }
 @Test
    public void dummyTestForBranch()
 {
     
 }




    }



