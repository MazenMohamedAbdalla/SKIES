package com.example.skies;

public class flights {
    private static String flightNumber;
    private static String departureTime;
    private static String arrivalTime;
    private static String departureDate;
    private static String arrivalDate;
    private static String duration;
    private static String source;
    private static String destination;
    private static String flightClass;
    private static String status;
    private static float cost;

    public String getFlightNumber() {return flightNumber;}
    public void setFlightNumber(String flightNumber) {flights.flightNumber = flightNumber;}
    public String getDepartureTime() {return departureTime;}
    public void setDepartureTime(String departureTime) {flights.departureTime = departureTime;}
    public String getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(String arrivalTime) {flights.arrivalTime = arrivalTime;}
    public String getDepartureDate() {return departureDate;}
    public void setDepartureDate(String departureDate) {flights.departureDate = departureDate;}
    public String getArrivalDate() {return arrivalDate;}
    public void setArrivalDate(String arrivalDate) {flights.arrivalDate = arrivalDate;}
    public String getDuration() {return duration;}
    public void setDuration(String duration) {flights.duration = duration;}
    public String getSource() {return source;}
    public void setSource(String source) {flights.source = source;}
    public String getDestination() {return destination;}
    public void setDestination(String destination) {flights.destination = destination;}
    public String getFlightClass() {return flightClass;}
    public void setFlightClass(String flightClass) {flights.flightClass = flightClass;}
    public String getStatus() {return status;}
    public void setStatus(String status) {flights.status = status;}
    public float getCost() {return cost;}
    public void setCost(float cost) {flights.cost = cost;}
}
