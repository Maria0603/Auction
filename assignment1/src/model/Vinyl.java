package model;




public class Vinyl{
    private String title, artist;
    private String borrower, reserver;
    private int year;
    private VinylState state;
    public Vinyl(String title, String artist, int year){
        this.title = title;
        this.artist = artist;
        this.year = year;
        state = new VinylAvailable();
    }

    public void borrowVinyl(String customer){
        state._borrow(this, customer);
    }

    @Override
    public String toString() {
        return getTitle() + ": (" + this.state + "), " + getBorrower() + " & " + getReserver();
    }

    public void returnVinyl(String customer){
        state._return(this,customer);
    }
    public void reserveVinyl(String customer){
        state._reserve(this,customer);
    }
    public String getStatus(){
        return getState().getStatus();
    }
    protected void setState(VinylState state){
        this.state = state;
    }
    public VinylState getState(){return state;}

    @Override
    public boolean equals(Object o) {
       // if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinyl vinyl = (Vinyl) o;
        return getTitle().equals(vinyl.getTitle()) && getArtist().equals(vinyl.getArtist()) && vinyl.getYear() == getYear();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String status(){
        return state.getClass().getSimpleName() + ", borw: " + getBorrower() + ", reserver: " + getReserver();
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

}
