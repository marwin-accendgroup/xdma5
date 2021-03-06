package com.gene.screenshots.jobs;

import com.gene.screenshots.base.ScreenshotJob;
import com.gene.screenshots.base.annotations.Environment;
import com.gene.screenshots.base.annotations.Job;
import com.gene.screenshots.selenium.perjeta.hcp.PerjetaHCP;

@Job(name = "Perjeta_HCP", ID = 6, info = "Screenshot automation for Perjeta HCP")
@Environment(local = "http://localhost:4503/content/perjeta/en_us",
        dev = "https://dev-perjeta.gene.com",
        stage = "https://stage-perjeta.gene.com",
        prod = "https://www.perjeta.com",
        authorlocal = "http://localhost:4502/content/perjeta/en_us",
        authordev = "https://dev-author.aem.gene.com/content/perjeta/en_us",
        authorprod = "https://author.aem.gene.com/content/perjeta/en_us",
        authorstage =  "https://stage-author.aem.gene.com/content/perjeta/en_us")

public class PerjetaHCPJob extends ScreenshotJob {

    public PerjetaHCPJob() {
    	
        setScript(new PerjetaHCP());
        
    }
}