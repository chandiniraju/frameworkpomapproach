package com.mavenit.pom.practise.page_elements;

import com.mavenit.pom.practise.Hooks;
import org.openqa.selenium.By;

public class BasketPage extends Hooks {

    public String getProductsInBasket()
    {
        return driver.findElement(By.xpath("//a[@data-element='BasketItemTitle']")).getText();
    }



}
