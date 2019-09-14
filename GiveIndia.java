class Demo {
 public static void main(String args[]){
   //Launch browser and URL
   System.setProperty("Webdriver.chromedriver", "./software/chromedriver.exe");
   ChromeDriver driver= new ChromeDriver();
   driver.get("https://en.wikipedia.org/wiki/Selenium");
   
  //Verify that the external links in “External links“ section work
   driver.findElement(By.xpath("//*[@id="toc"]/ul/li[10]/a/span[2]")).click();
   
  //Validate that the re-directed Url is correct
   String externalLinkUrl= driver.getCurrentUrl();
   Assert.assertEquals(externalLinkUrl, "https://en.wikipedia.org/wiki/Selenium#External_links");
   
  //Click on the “Oxygen” link on the Periodic table at the bottom of the page
   driver.finElement(By.xpath("//*[@id="mw-content-text"]/div/div[19]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[7]/a/span")).click();
   
  //Validate that the re-directed Url is correct
   String oxygenUrl= driver.getCurrentUrl();
   Assert.assertEquals(oxygenUrl, "https://en.wikipedia.org/wiki/Oxygen");
   
  //Verify that it is a “featured article”
   WebElement ImageFile = driver.findElement(By.xpath("//*[@id="mw-indicator-featured-star"]"));
        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
        if (!ImagePresent)
        {
             System.out.println("Image not displayed.");
        }
        else
        {
            System.out.println("Image displayed.");
        }
   
  //Take a screenshot of the right hand box that contains element properties
   		//Search for the box with element properties
        WebElement element = driver.findElement(By.xpath("//*[@id="mw-content-text"]/div/table[1]"));
 
        // Take screen shot of whole web page
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        // Calculate the width and height of the box element
        Point p = element.getLocation();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
 
        // Create Rectangle of same width as of the box element
        Rectangle rect = new Rectangle(width, height);
 		BufferedImage img = null;
        img = ImageIO.read(screenShot);
 
        //Crop Image of box element from the screen shot
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
 
        // write cropped image into File Object
        ImageIO.write(dest, "png", screenShot);
 
        //Copy Image into particular directory
        FileUtils.copyFile(screenShot,
                new File("D:/Recordings/WebElementScreenShot.png"));
   
  //Count the number of pdf links in “References"
   		//Identify Reference link and click to navigate to Reference section
   		driver.findElement(By.xpath("//*[@id="toc"]/ul/li[11]/a/span[2]")).click();
   		//Count the pdf links
   		Webelement div= driver.findElement(By.xpath("//*[@id="mw-content-text"]/div/div[47]"));
   		List<WebElement> links = div.findElements(By.tagName("a"));
   		
   		int count=0;
   		for (WebElement myElement : links){
          if (myElement.getText().contains(".pdf"){
            count++;
          }
        System.out.println("Count of pdf links:"+count);    
 
              
  //In the search bar on top right enter “pluto” and verify that the 2nd suggestion is “Plutonium”
    	//Scroll to the top of page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        //Find search box and enter "pluto"
        driver.findElement(By.xpath("//*[@id="simpleSearch"]")).sendKeys("Pluto");
        //Identify the dropdown menu 2nd element
        Select select = new Select(driver.findElement(By.id("searchform")));     
        WebElement option = select.selectByIndex(1);
		String actualText= option.getText();
        String expectedText= "Plutonium";
              
        Assert.assertEquals(actualText, expectedText);
    
  //Close the browser
        driver.close();
        driver.quit();
 }
}