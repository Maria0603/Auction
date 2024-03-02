package model;

public class VinylAvailable extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String borrower) {
        vinyl.setState(new VinylBorrowed());
        vinyl.setBorrower(borrower);

    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        throw new IllegalStateException("Vinyl is not borrowed");
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        vinyl.setState(new VinylReserved());
        vinyl.setReserver(reserver);

    }

    @Override
    public String getStatus() {
        return getClass().getSimpleName();
    }

}
