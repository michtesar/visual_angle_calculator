package VisualAngleConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    public RadioButton convertToggle;
    public Label item1;
    public Label item2;
    public Label item3;
    public Label item4;
    public Button convertButton;

    public String[] toAngleLabels = new String[4];
    public String[] toPixelsLabels = new String[4];
    public String[] convertLabels = new String[2];
    public boolean isVisualAngle = false;
    public TextField value1;
    public TextField value2;
    public TextField value3;
    public TextField value4;


    public void toggle(ActionEvent actionEvent) {
        if (convertToggle.isSelected()) {
            isVisualAngle = true;
            // Convert to visual angle
            item1.setText(toAngleLabels[0]);
            item2.setText(toAngleLabels[1]);
            item3.setText(toAngleLabels[2]);
            item4.setText(toAngleLabels[3]);
            convertButton.setText(convertLabels[0]);
        } else {
            // Convert to pixels
            isVisualAngle = false;
            item1.setText(toPixelsLabels[0]);
            item2.setText(toPixelsLabels[1]);
            item3.setText(toPixelsLabels[2]);
            item4.setText(toPixelsLabels[3]);
            convertButton.setText(convertLabels[1]);
        }
    }

    public void convert(ActionEvent actionEvent) {
        double item1Value = 0;
        double item2Value = 0;
        double item3Value = 0;
        double item4Value = 0;

        // Parse values from input text fields
        try {
            item1Value = Double.parseDouble(value1.getText());
            item2Value = Double.parseDouble(value2.getText());
            item3Value = Double.parseDouble(value3.getText());
            item4Value = Double.parseDouble(value4.getText());
        } catch(Exception e) {
            System.out.println("Cannot get proper values from input!" + e);
        }

        // Check if values are bigger than zero values
        if ((item1Value > 0) && (item2Value > 0) && (item3Value > 0) && (item4Value > 0)) {
            if (isVisualAngle) {
                // Convert to visual angle
                String result = convertToAngle(item1Value, item2Value, item3Value, item4Value);
                showResult(result);
            } else {
                // Convert to pixels
                String result = convertToPixels(item1Value, item2Value, item3Value, item4Value);
                showResult(result);
            }
        } else {
            showResult("Values are not OK, please check if they are bigger than zero!");
        }
    }

    @FXML
    public void initialize() {

        // Labels variations
        toAngleLabels[0] = "Screen height (cm)";
        toAngleLabels[1] = "Distance from screen (cm)";
        toAngleLabels[2] = "Vertical resolution (px)";
        toAngleLabels[3] = "Size of stimulus (px)";

        toPixelsLabels[0] = "Screen height (cm)";
        toPixelsLabels[1] = "Distance from screen (cm)";
        toPixelsLabels[2] = "Vertical resolution (px)";
        toPixelsLabels[3] = "Visual angle in degrees (°)";

        // Convert labels
        convertLabels[0] = "Convert to visual angle";
        convertLabels[1] = "Convert to pixels";

        // Fill the default values for converting to angle
        item1.setText(toPixelsLabels[0]);
        item2.setText(toPixelsLabels[1]);
        item3.setText(toPixelsLabels[2]);
        item4.setText(toPixelsLabels[3]);
        convertButton.setText(convertLabels[1]);
    }

    public String convertToAngle(double h, double d, double res, double size) {
        double degPerPix = ((Math.atan2(0.5*h, d)) / (0.5 * res)) * 180 / Math.PI;
        double sizeInDeg = size * degPerPix;
        String result = "Degrees per pixel is: " + degPerPix +
                        "\nSize in degrees is: " + sizeInDeg;
        return result;
    }

    public String convertToPixels(double h, double d, double res, double size) {
        double pixPerDeg = ((Math.atan2(0.5*h, d)) / (0.5 * res)) * 180 / Math.PI;
        double sizeInPix = size / pixPerDeg;
        String result = "Pixel per degree is: " + pixPerDeg +
                "\nSize in pixels is: " + sizeInPix;
        return result;
    }

    public void showResult(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Visual Angle Converter");
        alert.setHeaderText("Result");
        alert.setContentText(String.valueOf(result));
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
