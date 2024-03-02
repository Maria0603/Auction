package model;

public abstract class VinylState {


    private boolean scheduleRemoval;
    public VinylState()
    {
        this.scheduleRemoval=false;
    }
    public abstract void _borrow(Vinyl vinyl, String borrower);
    public abstract void _return(Vinyl vinyl, String borrower);
    public abstract void _reserve(Vinyl vinyl, String reserver);
    public abstract String getStatus();

    public void setRemove(boolean b){
        scheduleRemoval = b;
    }
    public boolean getRemove(){return scheduleRemoval;}


    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
