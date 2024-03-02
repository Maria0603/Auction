package viewmodel;

import model.VinylLibraryModel;
import view.ManageVinylViewController;

public class ViewModelFactory
{
  private ManageVinylViewModel manageVinylViewModel;
  private VinylListViewModel vinylListViewModel;
  private ViewModelState viewModelState;

  public ViewModelFactory(VinylLibraryModel model){
    viewModelState = new ViewModelState();
    vinylListViewModel = new VinylListViewModel(model, viewModelState);
    manageVinylViewModel = new ManageVinylViewModel(model, viewModelState);
  }

  public VinylListViewModel getVinylListViewModel() {
    return vinylListViewModel;
  }

  public ManageVinylViewModel getManageExerciseViewModel() {
    return manageVinylViewModel;
  }
}
