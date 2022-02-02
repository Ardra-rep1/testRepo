import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




public class UserCreation {



    public static void main(String[] args) {

        Helpers.navigateToUrl(fightObjects.url);
        createUser();
        selectChoice(fightObjects.answer1);
        selectChoice(fightObjects.answer2);
        selectChoice(fightObjects.answer1);
        selectChoice(fightObjects.answer1);
        selectChoice(fightObjects.answer2);
        selectChoice(fightObjects.answer2);
        selectChoice(fightObjects.answer1);
        selectChoice(fightObjects.answer2);
        selectChoice(fightObjects.answer2);
        selectChoice(fightObjects.answer1);
       Helpers.find(By.xpath(fightObjects.leaderBoard));
       WebElement table = Helpers.find(By.xpath(fightObjects.leaderBoardTable));
       List<WebElement> rowsList = table.findElements(By.tagName("tr"));
       List<WebElement> columnsList = null;
       for (WebElement row : rowsList) {
                columnsList = row.findElements(By.tagName("td"));

                for (WebElement column : columnsList) {
                    if(column.getText().equals(fightObjects.name)){
                        System.out.println("Your Score/n");
                        System.out.println(row.getText());
                    }
                }
            }


        Helpers.quit();
    }

    private static void selectChoice(String selection) {

            Helpers.JsExecutorClick(By.xpath(selection));
            Helpers.waitDriver(fightObjects.continueButton,50);
            Helpers.find(By.xpath(fightObjects.continueButton)).click();
    }



    private static void createUser() {
        Helpers.find(By.id(fightObjects.username)).sendKeys(fightObjects.name);
        Helpers.find(By.id(fightObjects.warriorButton)).click();



        if( Helpers.find(By.id(fightObjects.startButton)).getText().contains(fightObjects.name))
                System.out.println("User is created");

        Helpers.find(By.id(fightObjects.startButton)).click();
        String battleFieldText = Helpers.find(By.cssSelector(fightObjects.welcomeText)).getText();

        if(battleFieldText.contains(fightObjects.validatingText)){
            System.out.println("you are in to the battlefield");
        }
        Helpers.find(By.xpath(fightObjects.gameButton)).click();
        Helpers.find(By.xpath(fightObjects.gameStartButton)).click();
        String beginTextValidation = Helpers.find(By.xpath(fightObjects.gameBeginBtnValid)).getText();
        if(beginTextValidation.contains(fightObjects.beginText)){
            System.out.println("you are in to the game");
        }

    }



}