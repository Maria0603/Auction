package model;

public class VinylReservedWhileBorrowed extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String customer) {
        throw new IllegalStateException("Cannot borrow while borrowed");
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        if(vinyl.getBorrower().equals(borrower)){
            vinyl.setState(new VinylBorrowed());
            vinyl.setBorrower(vinyl.getReserver());
            vinyl.setReserver(null);
        }
        else{
            throw new IllegalStateException("Cannot if not borrower");
        }
    }

    @Override
    public void _reserve(Vinyl vinyl, String customer) {
        throw new IllegalStateException("Cannot reserved while borrowed");
    }

    @Override
    public String getStatus() {
        return getClass().getSimpleName();
    }
}
