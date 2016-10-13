using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using System.Threading;

//crete webdriver obj for firefox
        IWebDriver driver;
		 driver = new FirefoxDriver();

//login to webpage
 private void loginToPage(string nodeNo)
        {
            // driver = new FirefoxDriver();
            driver.Url = "http://10.83.11." + nodeNo + ":8081/url";
            driver.FindElement(By.Id("loginForm:j_username")).SendKeys(user);
            Thread.Sleep(intraDelay);
            driver.FindElement(By.Id("loginForm:j_password")).SendKeys(pass);
            Thread.Sleep(intraDelay);
            driver.FindElement(By.Id("loginForm:submitButton")).Click();
 
        }
		
		
// vist a specified page on partcular node
 private void goToPageAndPerfomrAction(long pageNo, string nodeNo)
        {
            driver.Url = "http://10.83.11." + nodeNo + ":8081/PMAdmin/stalledItems.do?pageNumber=" + pageNo + "&actionType=10";
            
            driver.FindElement(By.Name("master")).Click();
            Thread.Sleep(intraDelay);
            driver.FindElement(By.LinkText("Retry")).Click();

        }
		
//switch b/w multiple pages/url/port/servers load balanicing after a delay
private void btn_open_browser_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                pageNo = Convert.ToInt64( tb_page_no.Text);
                pageDelay = Convert.ToInt32(tb_page_delay.Text)*1000;//*1000 to convert it into milli seconds
                driver = new FirefoxDriver();
                loginToStallNodes("228");
                Thread.Sleep(intraDelay);

                loginToStallNodes("229");
                Thread.Sleep(intraDelay);

                driver.Url = "http://url";
               String nodeNo = "229";
                while (pageNo > 1)
                {
                    c++;
                    goToPageAndPerfomrAction(pageNo, nodeNo);
                    if (c >= 2)
                    {
                        if (nodeNo.Equals("229"))
                        {
                            nodeNo = "228";
                        }
                        else
                        {
                            nodeNo = "229";
                        }
                        c = 0;
                    }
                    pageNo--;
                    Thread.Sleep(pageDelay);
                }
                
            
            }
            catch (Exception e1)
            {
                lb_msg.Content = e1.Message;
            }
           

        }