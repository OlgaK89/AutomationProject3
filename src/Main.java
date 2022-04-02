
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class Main {


    public static void main(String[] arg) throws InterruptedException {

        System.setProperty("webdriver.edge.driver", "C:\\Users\\kobro\\Downloads\\Browsers\\edgedriver_win64\\msedgedriver.exe");


        WebDriver driver = new EdgeDriver();

        //Navigate  to carfax.com
        driver.get("https://www.carfax.com");

        driver.manage().window().maximize();
        Thread.sleep(1000);

        //Click on Find a Used Car
        driver.findElement(By.xpath("//a[(@href='/cars-for-sale')]")).click();

        String actualWord = driver.getTitle();
        String expectedWord = "Used Cars";
        //Verify the page title contains the word “Used Cars”
        Assert.assertTrue(actualWord.contains(expectedWord));

        Thread.sleep(1000);

        //Choose “Tesla” for  Make.
        driver.findElement(By.xpath("//a[(@href='#makeModelPanel')]"));
        driver.findElement(By.xpath("//select[@aria-label='Select Make']"));
        Thread.sleep(1000);

        Select select = new Select(driver.findElement(By.xpath("//select[@class='form-control search-make search-make--lp']")));

        select.selectByValue("Tesla");

        Thread.sleep(1000);

        //Verify that the Select Model dropdown box contains 4 current Tesla models - (Model 3, Model S, Model X, Model Y).

      driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']")).click();
      Thread.sleep(1000);
     List<String> models = Arrays.asList("Model 3", "Model S", "Model X", "Model Y");
    List <WebElement> options = driver.findElements(By.xpath("//optgroup[@class='current-models __web-inspector-hide-shortcut__']"));
      for(WebElement o : options) {
          Assert.assertTrue(o.equals(models));

      }
        Thread.sleep(1000);

       //	Choose “Model S” for Model.
        driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']")).click();
        driver.findElement(By.id("model_Model-S")).click();
        Thread.sleep(1000);

        //Enter the zip code 22182 and click Next
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/div/div/div/form/div[3]/div/div[4]/div/input")).sendKeys("22182");
        Thread.sleep(1000);
        driver.findElement(By.id("make-model-form-submit-button")).click();

        //Verify that the page contains the text “Step 2 – Show me cars with”
        String we = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[1]/h3")).getText();
        String step2 = "Step 2 - Show me cars with";

        Assert.assertTrue(we.contains(step2));

        Thread.sleep(1000);
         //Check all 4 checkboxes.
        driver.findElement(By.xpath("//*[@id=\"react-app-main\"]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[1]/label/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"react-app-main\"]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[2]/label/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"react-app-main\"]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[3]/label/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"react-app-main\"]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[4]/label/span[2]")).click();

        Thread.sleep(2000);

       //	Click on “Show me x Results” button.

          //Verify the results count by getting the actual number of results
        // displayed in the page by getting the count of WebElements that represent each result

        String count = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText();
        System.out.println(count);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();

        //Verify that each result header contains “Tesla Model S”.

        List<WebElement> optionsOfCars = (driver.findElements(By.xpath("//div[@class='listing-header']")));

        List<String> opt = new ArrayList<>();
        List<String> exp = Arrays.asList(" Tesla Model S ");
        Assert.assertEquals(count,""+optionsOfCars.size());
        for (WebElement o:optionsOfCars) {
            opt.add(o.getText());
        }

        boolean contains = false;
        for(String o : opt) {
            for(String e : exp) {
                if(o.contains(e)) {
                    contains=true;
                }
            }
        }
        if(contains){
            System.out.println("Contains Tesla Model S");
        }




        Thread.sleep(1000);



        //Get the price of each result
        // and save them into a List in the order of their appearance. (You can exclude “Call for price” options)
        driver.findElement(By.xpath("//div[@class='srp-list-container srp-list-container--srp']//div//div//div"));
        List<WebElement> elements = driver.findElements(By.className("srp-list-item-price"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        Thread.sleep(1000);

        //Choose “Price - High to Low” option from the Sort By menu
        driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")).click();

        driver.findElement(By.xpath("//*[@id=\"priceDesc\"]")).click();

        Thread.sleep(1000);

        ////Verify that the results are displayed from high to low price.
        List<WebElement> sorted = driver.findElements(By.cssSelector("srp-list-container srp-list-container--srp"));
        List<String> prices = new ArrayList<>();

        for (WebElement w : sorted) {
            prices.add(w.getText());
        }
        List<String> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        System.out.println(sortedPrices.equals(prices));


        Thread.sleep(1000);

        //Choose “Mileage - Low to High” option from Sort By menu
        driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")).click();
        driver.findElement(By.xpath("//*[@id=\"mileageAsc\"]")).click();

       //	Verify that the results are displayed from low to high mileage.
        List<String> mileage = new ArrayList<>();
        for (WebElement a : sorted) {
            mileage.add(a.getText());
        }
        List<String> sortedMileage = new ArrayList<>(mileage);
        Collections.sort(sortedMileage);
        System.out.println(sortedMileage.equals(mileage));

         Thread.sleep(1000);

        //Choose “Year - New to Old” option from Sort By menu
        driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")).click();
        driver.findElement(By.xpath("//*[@id=\"yearDesc\"]")).click();


        //Verify that the results are displayed from new to old year.
        List<String> year = new ArrayList<>();
        for(WebElement y: sorted){
            year.add(y.getText());
        }
        List<String> sortedYear = new ArrayList<>(year);
        Collections.sort(sortedYear);
        System.out.println(sortedYear.equals(year));

        driver.quit();
    }

}
