package model;

public class VinylReserved extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String borrower) {
        if(vinyl.getBorrower().equals(borrower)){
            vinyl.setBorrower(borrower);
            vinyl.setState(new VinylBorrowed());

        }
        else {
            throw new IllegalStateException("Cannot borrow unless is reserver");
        }
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        throw new IllegalStateException("Cannot reserver when not borrowed");
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        throw new IllegalStateException("Cannot be reserved when not borrowed");
    }

    @Override
    public String getStatus() {
        return getClass().getSimpleName();
    }
}
