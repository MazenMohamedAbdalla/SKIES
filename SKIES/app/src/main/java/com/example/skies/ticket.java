package com.example.skies;

public class ticket {
    private static int tick_id;
    private String passenger_name;
    private String flight_number;
    private int user_id;
    private int seat;
    private float cost;

    public  int getTick_id() {return tick_id;}
    public  void setTick_id(int tick_id) {ticket.tick_id = tick_id;}
    public String getPassenger_name() {return passenger_name;}
    public void setPassenger_name(String passenger_name) {this.passenger_name = passenger_name;}
    public String getFlight_number() {return flight_number;}
    public void setFlight_number(String flight_number) {this.flight_number = flight_number;}
    public int getUser_id() {return user_id;}
    public void setUser_id(int user_id) {this.user_id = user_id;}
    public int getSeat() {return seat;}
    public void setSeat(int seat) {this.seat = seat;}
    public float getCost() {return cost;}
    public void setCost(float cost) {this.cost = cost;}
}
