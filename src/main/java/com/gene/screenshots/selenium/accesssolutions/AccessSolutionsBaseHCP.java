package com.gene.screenshots.selenium.accesssolutions;

import com.gene.screenshots.selenium.ChromeDriverManager;
import com.gene.screenshots.selenium.SeleniumHeadless;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static com.gene.screenshots.selenium.Constants.*;

// custom functionality to get links and automate GATCF, fade in divs, and assistance tool for Access Solution brands
// removed all thread sleep calls and use WebDriverWait to determine when items are loaded.
// provides default mobile and desktop automation functions
public class AccessSolutionsBaseHCP extends SeleniumHeadless {

    public AccessSolutionsBaseHCP(){ brandName = getPdfName().toLowerCase(); }

    public AccessSolutionsBaseHCP(String pdfName){
        if(pdfName.contains("/")){
            String [] actualPdfName = pdfName.split("/");
            setPdfName(actualPdfName[actualPdfName.length - 1]);
        }else
            setPdfName(pdfName);
        this.brandName = pdfName.toLowerCase();
    }

    private String brandName;

    // custom AccessSolutionBase classes can use the name of the class itself for the pdf name
    protected String getBrandName() {
        return brandName;
    }

    @Override
    public String getSiteMapUrl() {
        return "/hcp/brands/" + getBrandName() + "/site-map.html";
    }

    @Override
    protected List<String> getLinksFromSiteMap(WebDriver driver) {
        List<String> links = super.getLinksFromSiteMap(driver);
        links.add("/hcp/brands/" + getBrandName() + ".html");
        links.add(getSiteMapUrl());
        
        return links;
    }


    @Override
    public String getSiteMapSelector() {
        return ".link a";
    }

    @Override
    public List<Thread> createScreenCaptureThreads(boolean isDesktop){
        List<Thread> threads = new ArrayList<>();

        WebDriver driver = ChromeDriverManager.requestDriver(isDesktop);

        List<String> links  = getLinksFromSiteMap(driver);
        setNumberOfPageVisits(links.size(), isDesktop);

        ChromeDriverManager.releaseDriver(driver, isDesktop);

        int pageNumber = 0;

        // create a thread per link
        for (String currentPage : links) {
            final int threadPageNumber = pageNumber++;
            threads.add(new Thread(() -> {

                // current thread has its own chrome driver
                WebDriver threadDriver =  ChromeDriverManager.requestDriver(isDesktop);
                try {
                    goToUrl(threadDriver, currentPage);
                    full(threadDriver, isDesktop, threadPageNumber);
                    getScreenshotForPAT(threadDriver, new Actions(threadDriver), isDesktop, threadPageNumber);
                    getScreenshotForLinkPopovers(threadDriver, isDesktop, threadPageNumber);
                    getScreenshotForAccordion(threadDriver, isDesktop, threadPageNumber);
                    getScreenshotForTabs(driver, isDesktop, threadPageNumber);
                } catch (Exception e) {
                    System.out.println("Issue at " + threadDriver.getCurrentUrl() + " for " + (isDesktop ? "desktop" : "mobile"));
                    e.printStackTrace();
                }
                ChromeDriverManager.releaseDriver(threadDriver, isDesktop);
            }));
        }

        return threads;
    }

    // fade in divs from popover links
    protected void getScreenshotForLinkPopovers(WebDriver driver, boolean ifDesktop, int pageIndex) {
        List<WebElement> links = driver.findElements(By.cssSelector("[data-toggle='popover']"));
        for (WebElement link : links) {
            if (!link.isDisplayed()) // on mobile some links are not visible?
                continue;
            if(!link.findElement(By.xpath("..")).getAttribute("class").contains("question"))
                full(driver, ifDesktop, pageIndex, link, ".popover", 400L);
        }
    }
    
    @Override
    protected void getScreenshotForTabs(WebDriver driver, boolean isDesktop, int pageIndex) {
    		
    	    List<WebElement> tabComponents = driver.findElements(By.cssSelector(".tabbed-chart"));
        for (int j = 0; j < tabComponents.size(); j++) {
            List<WebElement> tabs = tabComponents.get(j).findElements(By.cssSelector(".nav-tabs li"));
            if (tabs.size() > 1) {
                for (int i = 1; i < tabs.size(); i++) {
                    scrollTo(driver, 0, tabs.get(i).getLocation().getY());
                    tabs.get(i).click();
                    scrollTo(driver, 0, getCurrentScrollY(driver) + 1);
                    full(driver, isDesktop, pageIndex);
                }
            }
        }
    }
}