package com.gene.screenshots.selenium;

// What to do with the pdf(s)
public interface OutputResult {

    // read log create pdf or merge pdfs?
    void createResult();

    // send pdf(s)?
    void sendResult();
}
