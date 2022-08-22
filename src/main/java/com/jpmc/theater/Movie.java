package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
    	// return ticketPrice - getDiscount(showing.getSequenceOfTheDay());
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(),showing.getStartTime());
    }

    private double getDiscount(int showSequence,LocalDateTime time) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
      //Changes by Kaustubh
        double hourlyDiscount =0;
        if(time.getHour()>=11 && time.getHour()<=16) {
        	hourlyDiscount =ticketPrice * 0.25;
        }
        
        double seventhOfMonthDiscount =0;
        if(time.getDayOfMonth() == 7) {
        	seventhOfMonthDiscount = 1;
        }
      //Changes by Kaustubh
        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
        }
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }
      //Changes by Kaustubh
        if(hourlyDiscount !=0) {
        	return hourlyDiscount;
        }else if(specialDiscount !=0) {
        	return specialDiscount;
        }else if(sequenceDiscount !=0) {
        	return sequenceDiscount;
        }else if(seventhOfMonthDiscount !=0) {
        	return seventhOfMonthDiscount;
        }else {
        	return 0;
        }

        // biggest discount wins
       // return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
      //Changes by Kaustubh 

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }

	@Override
	public String toString() {
		return "Movie [title=" + title + ", description=" + description + ", runningTime=" + runningTime
				+ ", ticketPrice=" + ticketPrice + ", specialCode=" + specialCode + "]";
	}
    
    
}