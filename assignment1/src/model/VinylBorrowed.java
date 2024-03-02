package model;

public class VinylBorrowed extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String customer) {
        throw new IllegalStateException("Cannot borrow a borrowed vinyl");
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        if(vinyl.getBorrower() != null){
            if(vinyl.getBorrower().equals(borrower)){
                vinyl.setState(new VinylAvailable());
                vinyl.setBorrower(null);
            }
            else{
                throw new IllegalStateException("Cannot returned when not the borrower");
            }

        }
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        if(!vinyl.getBorrower().equals(reserver)){
            vinyl.setState(new VinylReservedWhileBorrowed());
            vinyl.setReserver(reserver);
        }
        else{
            throw new IllegalStateException("Cannot reserve by the same person");
        }

    }

    @Override
    public String getStatus() {
        return getClass().getSimpleName();
    }
}
