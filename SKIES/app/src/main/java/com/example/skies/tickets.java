package com.example.skies;

public class tickets {
    private int psnNumber;
    private String flightNumber; // ---
    private String userId;
    private String passengerName;
    private String source; // ---
    private String destination; // --
    private String ticketClass; // --
    private int ticketCost; // ---

    public void setPsnNumber(int psnNumber) {this.psnNumber = psnNumber; }
    public void setUserId(String useId) {this.userId = useId;}
    public void setPassengerName(String passengerName) {this.passengerName = passengerName;}
    public void setSource(String source) {this.source = source;}
    public void setDestination(String destination) {this.destination = destination;}
    public void setTicketClass(String ticketClass) {this.ticketClass = ticketClass;}
    public void setFlightNumber(String flightNumber) {this.flightNumber = flightNumber;}
    public void setTicketCost(int ticketCost) {this.ticketCost = ticketCost;}

    public String getFlightNumber() {return flightNumber;}
    public int getPsnNumber() {return psnNumber;}
    public String getUserId() {return userId;}
    public String getPassengerName() {return passengerName;}
    public String getSource() {return source;}
    public String getDestination() {return destination;}
    public String getTicketClass() {return ticketClass;}
    public int getTicketCost(){return ticketCost;}
}
