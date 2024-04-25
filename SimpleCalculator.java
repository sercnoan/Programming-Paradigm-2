import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SimpleCalculator extends Application
{

   @Override 
   public void start(Stage stage)
    {
    TextField calcTextField = new TextField();
        calcTextField.setMaxWidth(150);
        
    TextField calcTextField1 = new TextField();
        calcTextField1.setMaxWidth(150);

    
    Label resultLabel = new Label();
        resultLabel.setTextFill(Color.RED);
        resultLabel.setFont(Font.font("Stencil", 20));
    
    
        

     Button addButton = new Button();
     addButton.setText("+");
     addButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(calcTextField.getText());
                double num2 = Double.parseDouble(calcTextField1.getText());
                double result = num1 + num2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        });
     Button subButton = new Button();
     subButton.setText("-");
     subButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(calcTextField.getText());
                double num2 = Double.parseDouble(calcTextField1.getText());
                double result = num1 - num2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        });

     Button multButton = new Button();
     multButton.setText("x");
     multButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(calcTextField.getText());
                double num2 = Double.parseDouble(calcTextField1.getText());
                double result = num1*num2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        });
     
     Button divButton = new Button();
     divButton.setText("/");
    divButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(calcTextField.getText());
                double num2 = Double.parseDouble(calcTextField1.getText());
                double result = num1/num2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        });

    VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        

    root.getChildren().addAll(resultLabel,calcTextField, calcTextField1, addButton, subButton, multButton, divButton );
       
  
        // create a new scene
        Scene scene = new Scene(root, 300, 500);
        
        //add the scene to the stage, then configure the stage and make it visible
        stage.setScene(scene);
        stage.setTitle("Serc Noan Buque");
        stage.show();
    
    
}
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}