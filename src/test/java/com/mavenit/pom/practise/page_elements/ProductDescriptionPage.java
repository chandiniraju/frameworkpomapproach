package com.mavenit.pom.practise.page_elements;

import com.mavenit.pom.practise.Hooks;
import org.openqa.selenium.By;

public class ProductDescriptionPage extends Hooks {


    public void addProductToBasket()
    {

        driver.findElement(By.cssSelector("#product-actions .channels.space-b .space-b.center .Button-hYXUXp.fIBPaH")).click();
        driver.findElement(By.xpath("//button[text()='Continue to basket']")).click();

    }




}
