package main.GUI;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.InputMismatchException;

public class ViewModel
{
    private guiFunctions functions;
    private Model model;
    private IntegerProperty capacityProperty = new SimpleIntegerProperty();
    private StringProperty herstellerProperty = new SimpleStringProperty();
    private StringProperty kuchenProperty = new SimpleStringProperty();
    private StringProperty kremkuchenProperty = new SimpleStringProperty();
    private StringProperty obstkuchenProperty = new SimpleStringProperty();
    private StringProperty obsttorteProperty = new SimpleStringProperty();
    private StringProperty vhAllergeneProperty = new SimpleStringProperty();
    private StringProperty nvhAllergeneProperty = new SimpleStringProperty();


    public String getHersteller()
    {
        return herstellerProperty.get();
    }
    public String getKuchen()
    {
        return kuchenProperty.get();
    }


    public ViewModel()
    {
        model = new Model(functions);
        addInput = new TextField();
        removeInput = new TextField();
        displayInput = new TextField();
        inspectInput = new TextField();
        persistInput = new TextField();
        functions = new guiFunctions();
        left = new TextArea();
        right = new TextArea();
        leftText = new Text();
        rightText = new Text();
        //updateProperties();
    }

    public void updateProperties()
    {
        capacityProperty.set(functions.getAutomat().maxSize());
        herstellerProperty.set(functions.displayMode("hersteller"));
        kuchenProperty.set(functions.displayMode("kuchen Kuchen"));
        kremkuchenProperty.set(functions.displayMode("kuchen Kremkuchen"));
        obstkuchenProperty.set(functions.displayMode("kuchen Obstkuchen"));
        obsttorteProperty.set(functions.displayMode("kuchen Obsttorte"));
        vhAllergeneProperty.set(functions.displayMode("allergene i"));
        nvhAllergeneProperty.set(functions.displayMode("allergene e"));
    }
    //changes from title screen to function screen with continueButton
    public void continueButton() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/main/GUI/functions.fxml"));
        if(capacityInput.getText() != null)
        {
            functions.capacity(capacityInput.getText());
        }
        Stage window = (Stage) continueButton.getScene().getWindow();

        window.setScene(new Scene(root, 800, 800));
        updateProperties();
    }

    public void addButton() throws NullPointerException
    {
        if(addInput.getText() != null)
        {
            functions.addMode(addInput.getText());
        }
        else
            throw new NullPointerException("Eingabe ist null.");
        functions.getAutomat().listVerkaufsobjekte(2);
        updateProperties();
    }

    public void removeButton() throws NullPointerException
    {
        if(removeInput.getText() != null)
        {
            functions.deleteMode(removeInput.getText());
        }
        else
            throw new NullPointerException("Eingabe ist null.");

        updateProperties();
    }

    public void displayButton() throws InputMismatchException, NullPointerException
    {
        if(displayInput.getText() != null)
        {
            if(displayInput.getText().equals("hersteller"))
            {
                leftText.setText("Hersteller");
                left.textProperty().bind(herstellerProperty);
                nvhAllergeneProperty.unbind();
                vhAllergeneProperty.unbind();
            }
            else if(displayInput.getText().equals("kuchen Kuchen"))
            {
                rightText.setText("Kuchen");
                right.textProperty().bind(kuchenProperty);
                kremkuchenProperty.unbind();
                obstkuchenProperty.unbind();
                obsttorteProperty.unbind();
            }
            else if(displayInput.getText().equals("kuchen Kremkuchen"))
            {
                rightText.setText("Kremkuchen");
                right.textProperty().bind(kremkuchenProperty);
                kuchenProperty.unbind();
                obstkuchenProperty.unbind();
                obsttorteProperty.unbind();
            }
            else if(displayInput.getText().equals("kuchen Obstkuchen"))
            {
                rightText.setText("Obstkuchen");
                right.textProperty().bind(obstkuchenProperty);
                kremkuchenProperty.unbind();
                kuchenProperty.unbind();
                obsttorteProperty.unbind();
            }
            else if(displayInput.getText().equals("kuchen Obsttorte"))
            {
                rightText.setText("Obsttorte");
                right.textProperty().bind(obsttorteProperty);
                kremkuchenProperty.unbind();
                obstkuchenProperty.unbind();
                kuchenProperty.unbind();
            }
            else if(displayInput.getText().equals("allergene i"))
            {
                left.textProperty().bind(vhAllergeneProperty);
                leftText.setText("Vorhandene Allergene");
                nvhAllergeneProperty.unbind();
                herstellerProperty.unbind();
            }
            else if(displayInput.getText().equals("allergene e"))
            {
                left.textProperty().bind(nvhAllergeneProperty);
                leftText.setText("Nicht Vorhandene Allergene");
                vhAllergeneProperty.unbind();
                herstellerProperty.unbind();
            }
            else
                throw new InputMismatchException("Falsche Eingabe.");
        }
        else
            throw new NullPointerException("Eingabe ist null.");

        updateProperties();
    }

    public void inspectButton()
    {
        if(inspectInput.getText() != null)
        {
            functions.inspectionMode(inspectInput.getText());
        }
        else
            throw new NullPointerException("Eingabe ist null.");

        updateProperties();
    }

    public void persistButton()
    {
        if(persistInput.getText() != null)
        {
            functions.persistanceMode(persistInput.getText());
        }
        else
            throw new NullPointerException("Eingabe ist null.");

        updateProperties();
    }

    public void fachnummerButton()
    {
        functions.getAutomat().fachnummerSort();
        updateProperties();
    }

    public void herstellerButton()
    {
        functions.getAutomat().herstellerSort();
        updateProperties();
    }

    public void haltbarkeitButton()
    {
        functions.getAutomat().haltbarkeitSort();
        updateProperties();
    }

    @FXML
    Button continueButton;
    @FXML
    TextField capacityInput;
    @FXML
    TextField addInput;
    @FXML
    TextField removeInput;
    @FXML
    TextField displayInput;
    @FXML
    TextField inspectInput;
    @FXML
    TextField persistInput;
    @FXML
    Button addButton;
    @FXML
    Button removeButton;
    @FXML
    Button displayButton;
    @FXML
    Button inspectButton;
    @FXML
    Button persistButton;
    @FXML
    Button herstellerButton;
    @FXML
    Button fachnummerButton;
    @FXML
    Button haltbarkeitButton;
    @FXML
    TextArea left;
    @FXML
    TextArea right;
    @FXML
    Text leftText;
    @FXML
    Text rightText;
    @FXML
    private void initialize()
    {
        left.textProperty().bind(herstellerProperty);
        right.textProperty().bind(kuchenProperty);
        updateProperties();
    }
}
