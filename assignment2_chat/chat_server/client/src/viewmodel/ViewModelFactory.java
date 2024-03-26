package viewmodel;

import model.ChatModel;

public class ViewModelFactory {

  private LogInViewModel logInViewModel;
  private ChatViewModel chatViewModel;
  private ViewModelState viewState;

  public ViewModelFactory(ChatModel model){
    viewState = new ViewModelState();
    logInViewModel = new LogInViewModel(model, viewState);
    chatViewModel = new ChatViewModel(model, viewState);
  }
  public LogInViewModel getLogInViewModel() {
    return logInViewModel;
  }

  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }
}
