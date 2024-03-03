package model;

public class VinylAvailable extends VinylState{
    @Override
    public void _borrow(Vinyl vinyl, String borrower) {
        if(!vinyl.getToBeRemoved())
        {
            vinyl.setState(new VinylBorrowed());
            vinyl.setBorrower(borrower);
        }
        else throw new IllegalStateException("The vinyl will be removed");
    }

    @Override
    public void _return(Vinyl vinyl, String borrower) {
        throw new IllegalStateException("This vinyl is not borrowed");
    }

    @Override
    public void _reserve(Vinyl vinyl, String reserver) {
        if(!vinyl.getToBeRemoved())
        {
            vinyl.setState(new VinylReserved());
            vinyl.setReserver(reserver);
        }
        else throw new IllegalStateException("The vinyl will be removed");

    }

    @Override
    public String getStatus() {
        return "Available";
    }

}
