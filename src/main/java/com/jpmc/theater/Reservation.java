package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double totalFee() {
    	//Changes by Kaustubh
          // return showing.getMovieFee() * audienceCount;
    	     return showing.calculateFee(audienceCount);
    	//Changes by Kaustubh	 
    }

	@Override
	public String toString() {
		return "Reservation [customer=" + customer + ", showing=" + showing + ", audienceCount=" + audienceCount + "]";
	}
    
    
}