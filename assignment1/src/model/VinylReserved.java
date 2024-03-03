package model;

public class VinylReserved extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String borrower) {
        if(vinyl.getReserver().equals(borrower)){
            vinyl.setBorrower(borrower);
            vinyl.setReserver(null);
            vinyl.setState(new VinylBorrowed());
        }
        else {
            throw new IllegalStateException("You do not have a reservation for this vinyl");
        }

    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        throw new IllegalStateException("This vinyl is not borrowed");
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        throw new IllegalStateException("This vinyl is already reserved");
    }

    @Override
    public String getStatus() {
        return "Reserved";
    }
}
