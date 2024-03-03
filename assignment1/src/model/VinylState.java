package model;

public abstract class VinylState {



    public abstract void _borrow(Vinyl vinyl, String borrower);
    public abstract void _return(Vinyl vinyl, String borrower);
    public abstract void _reserve(Vinyl vinyl, String reserver);
    public abstract String getStatus();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
