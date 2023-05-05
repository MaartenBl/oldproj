require("@testing-library/jest-dom");
require("@testing-library/jest-dom/extend-expect");
require("@testing-library/react");
require("@testing-library/user-event");
require('chromedriver');
const {React} = require("react");
const {Builder, By, Key, util, } = require("selenium-webdriver");


// async function example() {
//     try {
//         // create webdriver
//         let driver = await new Builder().forBrowser("chrome").build();
//         await driver.manage().window().maximize();
//         // open url
//         await driver.get("http://localhost:3000/");
//         // find element name (q) in this case fot searchbar
//         // type "Selenium" in the element
//         // press enter (Key.RETURN)
//         await driver.findElement(By.name("IndexNavbarLoginBtn")).click();
//         // await driver.findElement(By.name("IndexNavbarLoginBtn")).sendKeys("Selenium", Key.RETURN)
//         setTimeout(async () => {
//             await driver.findElement(By.name("LoginEmail")).sendKeys("Selenium")
//         }, 1000);
//
//         setTimeout(async () => {
//             await driver.findElement(By.name("LoginPassword")).sendKeys("Password")
//         }, 1000);
//     } catch (e) {
//         console.log(e)
//     }
// }

test('navigates to login page', async () => {
    try {
        // create webdriver
        let driver = await new Builder().forBrowser("chrome").build();
        await driver.manage().window().maximize();
        // open url
        await driver.get("http://localhost:3000/");
        // find element name (q) in this case fot searchbar
        // type "Selenium" in the element
        // press enter (Key.RETURN)
        await driver.findElement(By.name("IndexNavbarLoginBtn")).click();
        // await driver.findElement(By.name("IndexNavbarLoginBtn")).sendKeys("Selenium", Key.RETURN)
        setTimeout(async () => {
            await driver.findElement(By.name("LoginEmail")).sendKeys("Selenium")
        }, 1000);


        setTimeout(async () => {
            await driver.findElement(By.name("LoginPassword")).sendKeys("Password")
        }, 1000);
        const search = await driver.findElement(By.name('LoginPassword')).getText();
        expect(search).toEqual("Password");
    } catch (e) {
        console.log(e)
    }
});
