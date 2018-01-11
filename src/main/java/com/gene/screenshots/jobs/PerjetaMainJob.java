package com.gene.screenshots.jobs;

import com.gene.screenshots.base.annotations.Environment;
import com.gene.screenshots.base.annotations.Job;
import com.gene.screenshots.base.ScreenshotJob;
import com.gene.screenshots.selenium.perjeta.main.PerjetaMain;

@Job(name = "Perjeta_Main", ID = 4, info = "Screenshot automation for Perjeta Main")
@Environment(local = "http://localhost:4503/content/perjeta/en_us",
        dev = "https://dev-perjeta.gene.com",
        stage = "https://stage-perjeta.gene.com",
        prod = "https://www.perjeta.com",
        authorlocal = "http://localhost:4502/content/perjeta/en_us",
        authordev = "https://dev-author.aem.gene.com/content/perjeta/en_us",
        authorprod = "https://author.aem.gene.com/content/perjeta/en_us",
        authorstage =  "http://stage-author.aem.gene.com/content/perjeta/en_us")

public class PerjetaMainJob extends ScreenshotJob {

    public PerjetaMainJob() {

        setScript(new PerjetaMain());

    }
}
