package model;

public class VinylBorrowed extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String customer) {
        throw new IllegalStateException("This vinyl is already borrowed");
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
            if(vinyl.getBorrower().equals(borrower)){
                vinyl.setState(new VinylAvailable());
                vinyl.setBorrower(null);
            }
            else{
                throw new IllegalStateException("You are not the borrower");
            }
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        if(!vinyl.getToBeRemoved())
        {
            if (!vinyl.getBorrower().equals(reserver))
            {
                vinyl.setState(new VinylReservedWhileBorrowed());
                vinyl.setReserver(reserver);
            }
            else
            {
                throw new IllegalStateException(
                    "You have already borrowed this vinyl");
            }
        }
        else throw new IllegalStateException("The vinyl will be removed");
    }

    @Override
    public String getStatus() {
        return "Borrowed";
    }
}
