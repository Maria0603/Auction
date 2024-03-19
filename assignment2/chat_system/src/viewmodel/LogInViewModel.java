package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;

public class LogInViewModel {
    private StringProperty headerProperty, usernameProperty, passwordProperty, errorProperty;

    private ChatModel model;
    private ViewModelState viewModelState;


    public LogInViewModel(ChatModel model, ViewModelState viewState) {
        this.model = model;
        this.viewModelState = viewState;

        headerProperty = new SimpleStringProperty("Create User!");
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        usernameProperty.set("");
        errorProperty.set("");
    }

    public void createUser() {

        try {
            model.createUser(getUsernameProperty().get(), getPasswordProperty().get());
            viewModelState.setUsername(getUsernameProperty().get());
            //System.out.println(viewModelState.getUsername());
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
    }

    public StringProperty getHeaderProperty() {
        return headerProperty;
    }


    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getPasswordProperty() {
        return passwordProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }


}
