package com.example.skies;

public class flights {
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;
    private String duration;
    private String from;
    private String to;
    private String eClass;
    private int seats;
    private String status;
    private int cost;

    public void setFlightNumber(String flightNumber) {this.flightNumber = flightNumber;}
    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}
    public void setArrivalTime(String arrivalTime) {this.arrivalTime = arrivalTime;}
    public void setDepartureDate(String departureDate) {this.departureDate = departureDate;}
    public void setArrivalDate(String arrivalDate) {this.arrivalDate = arrivalDate;}
    public void setDuration(String duration) {this.duration = duration;}
    public void setFrom(String from) {this.from = from;}
    public void setTo(String to) {this.to = to;}
    public void seteClass(String eClass) {this.eClass = eClass;}
    public void setSeats(int seats) {this.seats = seats;}
    public void setStatus(String status) {this.status = status;}
    public void setCost(int cost) {this.cost = cost; }

    public String getFlightNumber() {return flightNumber;}
    public String getDepartureTime() {return departureTime;}
    public String getArrivalTime() {return arrivalTime;}
    public String getDepartureDate() {return departureDate;}
    public String getArrivalDate() {return arrivalDate;}
    public String getDuration() {return duration;}
    public String getFrom() {return from;}
    public String getTo() {return to;}
    public String geteClass() {return eClass;}
    public int getSeats() {return seats;}
    public String getStatus() {return status;}
    public int getCost() {return cost; }
}
