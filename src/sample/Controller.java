package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    private GameField field = new GameField();

    private ImageView[][] cells = new ImageView[4][4];

    @FXML
    private ImageView id_i0j0, id_i0j1, id_i0j2, id_i0j3,
            id_i1j0, id_i1j1, id_i1j2, id_i1j3,
            id_i2j0, id_i2j1, id_i2j2, id_i2j3,
            id_i3j0, id_i3j1, id_i3j2, id_i3j3;

    @FXML
    private Button buttonUp, buttonLeft,
            buttonRight, buttonDown,
            buttonReset;

    @FXML
    private Text scoreTextField;

    private void setImages(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Image image = null;
                try {
                    image = new Image(new FileInputStream("resources/pictures/pic" + field[i][j] + ".png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                cells[i][j].setImage(image);
            }
        }
    }

    @FXML
    void initialize() {
        cells[0][0] = id_i0j0;
        cells[0][1] = id_i0j1;
        cells[0][2] = id_i0j2;
        cells[0][3] = id_i0j3;
        cells[1][0] = id_i1j0;
        cells[1][1] = id_i1j1;
        cells[1][2] = id_i1j2;
        cells[1][3] = id_i1j3;
        cells[2][0] = id_i2j0;
        cells[2][1] = id_i2j1;
        cells[2][2] = id_i2j2;
        cells[2][3] = id_i2j3;
        cells[3][0] = id_i3j0;
        cells[3][1] = id_i3j1;
        cells[3][2] = id_i3j2;
        cells[3][3] = id_i3j3;

        field.addRandom();
        field.addRandom();

        setImages(field.getGameField());

        buttonUp.setOnAction(event -> {
            field.action("UP");
            setImages(field.getGameField());
            scoreTextField.setText(field.getScoreString());
        });
        buttonDown.setOnAction(event -> {
            field.action("DOWN");
            setImages(field.getGameField());
            scoreTextField.setText(field.getScoreString());
        });
        buttonLeft.setOnAction(event -> {
            field.action("LEFT");
            setImages(field.getGameField());
            scoreTextField.setText(field.getScoreString());
        });
        buttonRight.setOnAction(event -> {
            field.action("RIGHT");
            setImages(field.getGameField());
            scoreTextField.setText(field.getScoreString());
        });
        buttonReset.setOnAction(event -> {
            field.resetField();
            field.addRandom();
            field.addRandom();
            setImages(field.getGameField());
            scoreTextField.setText("0");
            field.setScore(0);
        });
    }
}