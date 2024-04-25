import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;
public class NumberGuessing extends Application{
   private int randomNumber, attempt;
   @Override
   public void start(Stage stage){
      Random rand = new Random(); 
      int max = 100; // default max, 0 to 99
      randomNumber = rand.nextInt(max);
      attempt = 5; // default attempt
      
      // user inputed number (guess)
      TextField userInputNumber = new TextField();
      userInputNumber.setMaxWidth(250);
      userInputNumber.setFont(Font.font("Arial", 20));
      
      // submit button (verify guess)
      Button submit = new Button("Submit");
         
      // message for correct quess, wrong, less than, greater than, new number generated.
      Text msg = new Text(10, 20, "Guess the number");
      msg.setFont(Font.font("Arial", 15));
      
      // message fore attempt/left
      Text msgAttempt = new Text(10, 20, "You have "+attempt+" attempt/s left");
      msgAttempt.setFont(Font.font("Arial", 20));
      // show the max number of rand (max = 100 ) 0 to 100
      Text text = new Text(10, 20, "Max random number : "+max); 
      text.setFont(Font.font("Arial", 15));
      //
      System.out.println(randomNumber);
      // if the submit button is click, it will execute this line of code
      submit.setOnAction( 
         e -> {
            if(attempt > 0){
               int guess = Integer.parseInt(userInputNumber.getText());
               if(guess > randomNumber){
                  msg.setText("Number to high");
                  msg.setFill(Color.RED);
                  attempt -= 1;
                  msgAttempt.setText("You have " + attempt + " attempt/s left");                  }
               else if(guess < randomNumber){
                  msg.setText("Number to low");
                  msg.setFill(Color.RED);
                  attempt -= 1;
                  msgAttempt.setText("You have " + attempt + " attempt/s left");
               }
               else{
                  msg.setText("Congratulation for guessing the correct number");
                  msg.setFill(Color.GREEN);
                  randomNumber = rand.nextInt(max);
                  attempt = 5;
                  msgAttempt.setText("New number generated\nYou have " + attempt + " attempt/s left");
               }
            }
            else{
               randomNumber = rand.nextInt(max);
               attempt = 5;
               msgAttempt.setText("GAME OVER!!!\nNew Random number is generated\nAttempt Left "+attempt);
              
            } 
         });
     
      
      VBox root = new VBox();
      root.setSpacing(10);
      root.setAlignment(Pos.CENTER);
   
      root.getChildren().addAll(msgAttempt, text, userInputNumber, submit, msg);
   
      Scene scene = new Scene(root, 400, 400);
      stage.setScene(scene);
      stage.setTitle("Serc Noan Buque");
      stage.show();
   }
   
   
   public static void main(String[] args){
      launch(args);
   }
  
}