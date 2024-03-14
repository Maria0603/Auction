package viewmodel;

import model.Model;


public class ViewModelFactory
{
  private ManageVinylViewModel manageVinylViewModel;
  private VinylListViewModel vinylListViewModel;
  private ViewState viewModelState;

  public ViewModelFactory(Model model){
    viewModelState = new ViewState();
    vinylListViewModel = new VinylListViewModel(model, viewModelState);
    manageVinylViewModel = new ManageVinylViewModel(model, viewModelState);
  }

  public VinylListViewModel getVinylListViewModel() {
    return vinylListViewModel;
  }

  public ManageVinylViewModel getManageVinylViewModel() {
    return manageVinylViewModel;
  }
}
