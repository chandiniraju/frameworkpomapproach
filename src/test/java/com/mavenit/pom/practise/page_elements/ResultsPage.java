package com.mavenit.pom.practise.page_elements;

import com.mavenit.pom.practise.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.mavenit.pom.practise.testutils.RandomNumberHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ResultsPage extends Hooks {


    public String getPageTitle() {

        return driver.findElement(By.className("pageTitle")).getText();
    }

    public String getThumbNail() {
        return driver.findElement(By.cssSelector(".breadcrumb .current")).getText();
    }

    public String selectAnyProduct() {

        List<WebElement> productWebelements = driver.findElements(By.className("productTitle"));
        int productCount = productWebelements.size();
        int randomNumber = new RandomNumberHelper().generateRandomNumber(productCount);  // to access RandomNumberhelper class ,create
        // object new RandomClassHelper(), then access the
        // method generateRandomNumber

        String productSelected = productWebelements.get(randomNumber).getText();
        System.out.println("productSelected  is  " +productSelected);
        productWebelements.get(randomNumber).click();
        return productSelected;

    }

    public void selectPrice(String priceRange) {
        int counter = 0;
        List<WebElement> filters = driver.findElements(By.cssSelector(".dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper"));
        // List<WebElement> filters=driver.findElements(By.xpath("//ul[@class='dc-filter__option-list']/li"));
        //List<WebElement> filters=driver.findElements(By.cssSelector(".dc-filter dc-filter--selected .dc-filter__header .dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper .dc-checkbox dc-checkbox-extra-small .dc-checkbox-label"));
        // List<WebElement> filters=driver.findElements(By.xpath("//aside[@id='filters']/div[1]/section/div/div[2]/nav[1]/ul"));
        //List<WebElement> filters = driver.findElements(By.cssSelector(".dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper .dc-checkbox dc-checkbox-extra-small .dc-checkbox-label"));
        for (WebElement filter : filters) {

            if (filter.getText().equalsIgnoreCase(priceRange)) {
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                counter++;
                System.out.println(counter);

                filter.click();
                break;
            }

        }
        if (counter == 0) {
            fail("filter choice is not available" + priceRange);


        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        public List<Double> getAllProductsPrices()
        {
            List<Double> collectedPrices = new ArrayList<Double>();
            List<WebElement> priceWebElements = driver.findElements(By.cssSelector(".price"));
            for (WebElement priceWebelement : priceWebElements) {


                String priceInString = priceWebelement.getText().replace("£", "");
                double priceInDouble = Double.valueOf(priceInString);
                collectedPrices.add(priceInDouble);

            }

            if (collectedPrices.size() == 0) {
                fail("No products collected");
            }
            return collectedPrices;

        }
}
