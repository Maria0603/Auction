package model;

public class VinylReservedWhileBorrowed extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String customer) {
        throw new IllegalStateException("This vinyl is already borrowed");
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        if(vinyl.getBorrower().equals(borrower)){
            vinyl.setState(new VinylReserved());
            vinyl.setBorrower(null);
        }
        else{
            throw new IllegalStateException("You did not borrow this vinyl");
        }
    }

    @Override
    public void _reserve(Vinyl vinyl, String customer) {
        throw new IllegalStateException("This vinyl is already reserved");
    }

    @Override
    public String getStatus() {
        return "Reserved and Borrowed";
    }
}
