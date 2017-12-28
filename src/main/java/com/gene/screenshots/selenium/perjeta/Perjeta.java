package com.gene.screenshots.selenium.perjeta;

import com.gene.screenshots.selenium.SeleniumHeadless;
import org.openqa.selenium.*;

public class Perjeta extends SeleniumHeadless{

    public static void main(String [] args){

        new Perjeta().mobileAutomationTest("");
    }

    @Override
    public void mobileAutomationTest(String savePath) {
        WebDriver driver = null;
        try {
            driver = makeMobileDriver();
            driver.get("http://www.perjeta.com");
            Thread.sleep(1000);
            forceClick(driver, "/html/body/section[2]/div[4]/div/footer/a[2]");

            Thread.sleep(1000);
            full(driver, false, "perjeta", "Patient_Eligibility");
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[1]/header");

            Thread.sleep(1000);
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[2]/header");
            full(driver, false, "perjeta", "Design_Trail");
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[2]/header");

            Thread.sleep(1000);
            forceClick(driver, ("/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[3]/header"));
            full(driver, false, "perjeta", "Efficacy_data");
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[3]/header");

            Thread.sleep(1000);
            forceClick(driver, ("/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[4]/header"));
            full(driver, false, "perjeta", "Safety_profile");
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[4]/header");

            Thread.sleep(1000);
            forceClick(driver, "/html/body/main/section[3]/div[1]/div[2]/div/div[2]/section[5]/header");
            full(driver, false, "perjeta", "Dosing");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(driver != null)
                driver.quit();
        }

    }
}
