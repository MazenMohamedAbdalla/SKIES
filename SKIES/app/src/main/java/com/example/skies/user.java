package com.example.skies;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class user {
    private static int id;
    private static String name;
    private static String email;
    private static String pass;

    public void setId(int id) {
        user.id = id;
    }
    public void setName(String name) {
        user.name = name;
    }
    public void setEmail(String email) {
        user.email = email;
    }
    public void setPass(String pass) {
        user.pass = pass;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPass() {
        return pass;
    }


    DB_Connection connect = new DB_Connection();
    Connection myDb = connect.Connect_DB();
    bankAccountInfo account = new bankAccountInfo();
    flights flight = new flights();
    airplanes airplane = new airplanes();
    ticket tick = new ticket();

    // LOG IN DONE !
    public boolean logIn(String email, String pass) {
        setEmail(email);
        setPass(pass);
        boolean loggedIn = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM skies.users WHERE email = '" + getEmail() + "' AND pass = '" + getPass() + "'");
            if (query.next()) {
                setId(query.getInt("id"));
                setName(query.getString("name"));
                stm.close();
                loggedIn = true;
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return loggedIn;
    }

    // SIGN UP DONE !
    public boolean signUp(String name, String email, String pass, String confirmPass) {
        setName(name);
        setEmail(email);
        setPass(pass);
        boolean signedUp = false;

        try {
            Statement stm = myDb.createStatement();
            if (getPass().equals(confirmPass)) {
                int insertion = stm.executeUpdate("INSERT INTO skies.users (name,email,pass) VALUES ('" + getName() + "','" + getEmail() + "','" + getPass() + "')");
                //ResultSet query = stm.executeQuery("INSERT INTO users (name,email,pass,con_pass) VALUES ('"+getName()+"','"+getEmail()+"','"+getPass()+"')");
                if (insertion > 0) {
                    stm.close();
                    signedUp = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return signedUp;
    }

    // RECOVER DONE !
    public boolean recoverPassword(String email) {
        setEmail(email);
        String pass = null;
        boolean found = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT pass FROM skies.users WHERE email = '" + getEmail() + "'");
            if (query.next()) {
                pass = query.getString("pass");
                setPass(pass);
                found = true;
                stm.close();
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return found;
    }

    // CHANGE PASSWORD DONE !
    public boolean changePassword(String oldPass, String newPass) {
        boolean changed = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM skies.users WHERE id = '" + getId() + "' AND pass = '" + oldPass + "'");
            if (query.next()) {
                int upDate = stm.executeUpdate("UPDATE skies.users SET pass = '" + newPass + "' WHERE id = '" + getId() + "';");
                if (upDate > 0) {
                    stm.close();
                    changed = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return changed;
    }

    // CHANGE EMAIL DONE !
    public boolean changeEmail(String newEmail, String pass) {
        boolean changed = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM skies.users WHERE email = '" + getEmail() + "' AND pass = '" + pass + "'");
            if (query.next()) {
                int upDate = stm.executeUpdate("UPDATE skies.users SET email = '" + newEmail + "' WHERE id = '" + getId() + "';");
                if (upDate > 0) {
                    setEmail("newEmail");
                    stm.close();
                    changed = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return changed;
    }

    // CHANGE NAME DONE !
    public boolean changeName(String newName) {
        boolean changed = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM skies.users WHERE id = '" + getId() + "';");
            if (query.next()) {
                int upDate = stm.executeUpdate("UPDATE skies.users SET name = '" + newName + "' WHERE id = '" + getId() + "';");
                if (upDate > 0) {
                    setName(newName);
                    stm.close();
                    changed = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return changed;
    }

    // FEEDBACK DONE !
    public boolean userFeedback(String feedback) {
        boolean added = false;
        try {
            Statement stm = myDb.createStatement();
            int query = stm.executeUpdate("INSERT INTO skies.user_feedback VALUES ('" + getId() + "','" + feedback + "');");
            if (query > 0) {
                stm.close();
                added = true;
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return added;
    }

    // ADD BANKACC DONE !
    public boolean addBankAccount(String cardNumber, String cardHolder, String expDate, String cvv) {
        account.setCardNumber(cardNumber);
        account.setCardHolder(cardHolder);
        account.setExpDate(expDate);
        account.setCvv(cvv);
        boolean added = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '" + account.getCardNumber() + "' AND card_holder = '" + account.getCardHolder() + "' AND expiry_date = '" + account.getExpDate() + "' AND cvv = '" + account.getCvv() + "';");
            if (query.next()) {
                account.setBalance(query.getFloat("balance"));
                int query2 = stm.executeUpdate("INSERT INTO skies.bank_accounts VALUES ('"+account.getCardNumber()+"','"+getId()+"','"+account.getBalance()+"','"+account.getCardHolder()+"');");
                if (query2 > 0) {
                    stm.close();
                    added = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return added;
    }

    // DELETE BANKACC DONE !
    public boolean deleteBankAccount(String cardNumber, String cvv) {
        boolean deleted = false;
        account.setCardNumber(cardNumber);
        account.setCvv(cvv);
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '" + account.getCardNumber() + "' AND cvv = '" + account.getCvv() + "';");
            if (query.next()) {
                int query2 = stm.executeUpdate("DELETE FROM skies.bank_accounts WHERE account_number = '" + account.getCardNumber() + "';");
                if (query2 > 0) {
                    stm.close();
                    deleted = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return deleted;
    }

    // UPDATE BANKACC DONE !
    public boolean updateBankAccount(String oldCardNumber, String newCardNumber, String cardHolder, String expDate, String cvv) {
        account.setCardNumber(newCardNumber);
        account.setCardHolder(cardHolder);
        account.setExpDate(expDate);
        account.setCvv(cvv);
        boolean updated = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '" + account.getCardNumber() + "' AND card_holder = '" + account.getCardHolder() + "' AND expiry_date = '" + account.getExpDate() + "' AND cvv = '" + account.getCvv() + "';");
            if (query.next()) {
                account.setBalance(query.getInt("balance"));
                int query2 = stm.executeUpdate("UPDATE skies.bank_accounts SET account_number = '" + account.getCardNumber() + "', account_balance = '" + account.getBalance() + "' WHERE account_number = '" + oldCardNumber + "';");
                if (query2 > 0) {
                    stm.close();
                    updated = true;
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return updated;
    }

    // VIEW BACCOUNT DONE !
    public ArrayList<ArrayList<String>> viewBankAccount() {
        ArrayList<ArrayList<String>> accounts = new ArrayList<ArrayList<String>>();

        ArrayList<String> cardHolder = new ArrayList<String>();
        ArrayList<String> accountN = new ArrayList<String>();
        ArrayList<String> accBalance = new ArrayList<String>();
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM skies.bank_accounts WHERE user_id = '"+getId()+"';");
            while(query.next()) {
                String accountNo = query.getString("account_number");
                String balance = Float.toString(query.getFloat("account_balance"));
                cardHolder.add(query.getString("card_holder"));
                accountN.add(accountNo);
                accBalance.add(balance);
            }
            accounts.add(cardHolder);
            accounts.add(accountN);
            accounts.add(accBalance);
            stm.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // SEARCH FLIGHTS (ONE) DONE !
    public ArrayList<ArrayList<String>> searchFlight(String source, String destination, String departureDate, String flightClass) {
        ArrayList<ArrayList<String>> flightResults = new ArrayList<ArrayList<String>>(); // resultSet

        ArrayList<String> flightNumber = new ArrayList<String>(); //flightNumbers
        ArrayList<String> from = new ArrayList<String>(); // source
        ArrayList<String> to = new ArrayList<String>(); // destination
        ArrayList<String> departure_Date = new ArrayList<String>(); // departure date
        ArrayList<String> departure_time = new ArrayList<String>(); // departure time

        ArrayList<String> arrivalDate = new ArrayList<String>(); // arrival date
        ArrayList<String> arrivalTime = new ArrayList<String>(); // arrival time
        ArrayList<String> cost = new ArrayList<String>(); // cost
        ArrayList<String> numberOfSeats = new ArrayList<String>(); // N.seats
        flight.setSource(source);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setFlightClass(flightClass);
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT flight_number, source_from, destination_to,DATE(departure),TIME(departure),DATE(arrival),TIME(arrival),numberOfSeats,class,cost FROM skies.one_way_flights JOIN skies.plane ON one_way_flights.plane_id = plane.airplaneId\n"
                    + "WHERE source_from = '"+flight.getSource()+"' AND destination_to = '"+flight.getDestination()+"' AND DATE(departure) = '"+flight.getDepartureDate()+"'\n"
                    + "AND class = '"+flight.getFlightClass()+"';");
            while(query.next()) {
                flightNumber.add(query.getString("flight_number")); // flightNumber
                from.add(query.getString("source_from")); // source
                to.add(query.getString("destination_to")); // destination
                departure_Date.add(query.getString("DATE(departure)")); // departure date
                String time_1 = query.getString("TIME(departure)"); // departure time
                departure_time.add(time_1.substring(0,5));
                arrivalDate.add(query.getString("DATE(arrival)")); // arrival date
                String time_2 = query.getString("TIME(arrival)"); // arrival time
                arrivalTime.add(time_2.substring(0,5));
                cost.add(query.getString("Cost")); // cost
                numberOfSeats.add(query.getString("numberOfSeats"));
            }
            flightResults.add(flightNumber);
            flightResults.add(from);
            flightResults.add(to);
            flightResults.add(departure_Date);
            flightResults.add(departure_time);
            flightResults.add(arrivalDate);
            flightResults.add(arrivalTime);
            flightResults.add(cost);
            flightResults.add(numberOfSeats);
            stm.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flightResults;
    }

    // FLIGHT STATUS DONE !
    public boolean flightStatus(String flightNumber) {
        flight.setFlightNumber(flightNumber);
        boolean found = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT current_status,source_from,destination_to,TIME(departure),DATE(departure),TIME(arrival),DATE(arrival) FROM skies.one_way_flights\n"
                    + "WHERE flight_number = '" + flight.getFlightNumber() + "';");
            if (query.next()) {
                flight.setStatus(query.getString("current_status"));
                flight.setSource(query.getString("source_from"));
                flight.setDestination(query.getString("destination_to"));
                String time_1 = query.getString("TIME(departure)");
                flight.setDepartureTime(time_1.substring(0,5));
                flight.setDepartureDate(query.getString("DATE(departure)"));
                String time_2 = query.getString("TIME(arrival)"); // arrival time
                flight.setArrivalTime(time_2.substring(0,5));
                flight.setArrivalDate(query.getString("DATE(arrival)"));
                stm.close();
                found = true;
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return found;
    }

    // BOOKFLIGHT (TICKET) DONE !
    public boolean bookFlight(String flightNumber, String passengerName, String cardNumber) {
        boolean booked = false;
        flight.setFlightNumber(flightNumber);
        account.setCardNumber(cardNumber);
        try {
            // ----------
            Statement stm = myDb.createStatement();
            ResultSet flightResult = stm.executeQuery("SELECT * FROM skies.one_way_flights JOIN  skies.plane_seats ON one_way_flights.plane_id = plane_seats.plane_id\n"
                    + "WHERE flight_number = '"+flight.getFlightNumber()+"' AND seat_status = 'Empty';");


            if (flightResult.next()) {
                airplane.setAirplaneId(flightResult.getInt("plane_id")); // AIR ID
                tick.setCost(flightResult.getFloat("Cost"));
            }
            // ---------
            // ---------
            Statement stm2 = myDb.createStatement();
            ResultSet balance = stm2.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '"+account.getCardNumber()+"'");

            if(balance.next()) {
                account.setBalance(balance.getFloat("balance"));
            }
            // ---------
            // ---------
            Statement stm3 = myDb.createStatement();
            ResultSet airplanes =  stm3.executeQuery("SELECT * FROM skies.plane WHERE airplaneId = '"+airplane.getAirplaneId()+"'");

            if(airplanes.next()) {
                airplane.setNumberOfSeats(airplanes.getInt("numberOfSeats"));
            }

            // --------
            int numberOfSeats = airplane.getNumberOfSeats();
            float accBalance = account.getBalance();
            float ticketCost = tick.getCost();
            int airId = airplane.getAirplaneId();
            // --------


            Statement stm4 = myDb.createStatement();
            if (numberOfSeats > 0 && accBalance >= ticketCost) {
                accBalance = accBalance - ticketCost;
                numberOfSeats -= 1;
                //-------
                tick.setFlight_number(flightResult.getString("flight_number"));
                tick.setSeat(flightResult.getInt("S_ID")); // SEAT ID
                tick.setPassenger_name(passengerName);
                //-------

                int insertQuery = stm4.executeUpdate("INSERT INTO skies.ticket (passenger_name,flight_number,user_id,seat,cost)\n"
                        + "VALUES ('"+tick.getPassenger_name()+"','"+tick.getFlight_number()+"','"+getId()+"','"+tick.getSeat()+"','"+ticketCost+"');");
                int updateBalance = stm4.executeUpdate("UPDATE bank.credit_cards SET balance = '"+accBalance+"' WHERE card_number = '"+account.getCardNumber()+"';");
                int updateBalance_2 = stm4.executeUpdate("UPDATE skies.bank_accounts SET account_balance = '"+accBalance+"' WHERE account_number = '"+account.getCardNumber()+"'"); // -----
                int decNumberOfSeats = stm4.executeUpdate("UPDATE skies.plane SET numberOfSeats = '"+numberOfSeats+"' WHERE airplaneId = '"+airId+"'");
                int checkBooked =  stm4.executeUpdate("UPDATE skies.plane_seats SET seat_status = 'Booked' WHERE S_ID = '"+tick.getSeat()+"'");

                if(insertQuery > 0 && updateBalance > 0 && decNumberOfSeats > 0  && checkBooked > 0 && updateBalance_2 > 0) {

                    booked = true;
                }
            }
        }catch(Exception e) {
            Log.e("Error",e.getMessage());
        }
        return booked;
    }

    // CANCEL FLIGHT DONE !
    public boolean cancelFlight(String flightNumber, String passengerName) {
        flight.setFlightNumber(flightNumber);
        tick.setPassenger_name(passengerName);
        boolean canceled = false;
        try {
            // -----
            Statement stm2 = myDb.createStatement();
            ResultSet balance = stm2.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '"+account.getCardNumber()+"'");
            if(balance.next()) {
                account.setBalance(balance.getFloat("balance"));
            }
            // -----
            Statement stm3 = myDb.createStatement();
            ResultSet flightResult = stm3.executeQuery("SELECT * FROM skies.one_way_flights JOIN  skies.plane_seats ON one_way_flights.plane_id = plane_seats.plane_id\n"
                    + "WHERE flight_number = '"+flight.getFlightNumber()+"' AND seat_status = 'Booked';");
            if(flightResult.next()) {
                airplane.setAirplaneId(flightResult.getInt("plane_id"));
                tick.setSeat(flightResult.getInt("S_ID"));
            }
            // -----
            // -----
            Statement stm4 = myDb.createStatement();
            ResultSet airplanes =  stm4.executeQuery("SELECT * FROM skies.plane WHERE airplaneId = '"+airplane.getAirplaneId()+"'");
            if(airplanes.next()) {
                airplane.setNumberOfSeats(airplanes.getInt("numberOfSeats"));
            }
            // -----
            // -----

            Statement stm = myDb.createStatement();
            ResultSet bookedTicket = stm.executeQuery("SELECT * FROM skies.ticket\r\n"
                    + "WHERE flight_number = '"+flight.getFlightNumber()+"' AND passenger_name = '"+tick.getPassenger_name()+"';");

            int numberOfSeats = airplane.getNumberOfSeats();
            int airId = airplane.getAirplaneId();
            int seat_Id = tick.getSeat();
            float accBalance = account.getBalance();
            float paidTicket = 0;

            Statement stm5 = myDb.createStatement();

            if(bookedTicket.next()) {
                tick.setTick_id(bookedTicket.getInt("tick_id"));
                tick.setCost(bookedTicket.getFloat("cost"));
                paidTicket = tick.getCost();
                accBalance = (float) (accBalance + (paidTicket*(0.6)));
                numberOfSeats += 1;
                // -----

                int cancelQuery = stm5.executeUpdate("Delete FROM skies.ticket WHERE tick_id = '"+tick.getTick_id()+"';");
                int updateBalance = stm5.executeUpdate("UPDATE bank.credit_cards SET balance = '"+accBalance+"' WHERE card_number = '"+account.getCardNumber()+"';");
                int updateBalance_2 = stm5.executeUpdate("UPDATE skies.bank_accounts SET account_balance = '"+accBalance+"' WHERE account_number = '"+account.getCardNumber()+"';"); // -----
                int decNumberOfSeats = stm5.executeUpdate("UPDATE skies.plane SET numberOfSeats = '"+numberOfSeats+"' WHERE airplaneId = '"+airId+"'");
                int checkBooked =  stm5.executeUpdate("UPDATE skies.plane_seats SET seat_status = 'Empty' WHERE S_ID = '"+seat_Id+"'");

                if (cancelQuery > 0 && updateBalance > 0 && decNumberOfSeats > 0  && checkBooked > 0 && updateBalance_2>0) {
                    canceled = true;
                }
                // -----
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return canceled;
    }

    // MYBOOKINGS DONE !
    public ArrayList<ArrayList<String>> myBookings() {
        ArrayList<ArrayList<String>> bookings = new ArrayList<ArrayList<String>>();

        ArrayList<String> passengerN = new ArrayList<String>();
        ArrayList<String> tickN = new ArrayList<String>();
        ArrayList<String> ticketC = new ArrayList<String>();
        ArrayList<String> seatNumber = new ArrayList<String>();
        ArrayList<String> sourceFrom = new ArrayList<String>();
        ArrayList<String> destinationTo = new ArrayList<String>();
        ArrayList<String> departureDATE = new ArrayList<String>();
        ArrayList<String> departureTime = new ArrayList<String>();
        ArrayList<String> arrivalDate = new ArrayList<String>();
        ArrayList<String> arrivalTime = new ArrayList<String>();
        ArrayList<String> flightClass = new ArrayList<String>();

        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT passenger_name,ticket.flight_number,ticket.cost,seat_number,source_from,destination_to,TIME(departure),DATE(departure),TIME(arrival),DATE(arrival),class\r\n"
                    + "FROM skies.ticket \n"
                    + "JOIN skies.plane_seats ON ticket.seat = plane_seats.S_ID\n"
                    + "JOIN skies.one_way_flights ON one_way_flights.flight_number = ticket.flight_number\n"
                    + "WHERE user_id = '"+getId()+"';");
            while(query.next()) {
                passengerN.add(query.getString("passenger_name"));
                tickN.add(query.getString("ticket.flight_number"));
                ticketC.add(Float.toString(query.getFloat("ticket.cost"))); // ticket.cost
                seatNumber.add(query.getString("seat_number")); // seat_number
                sourceFrom.add(query.getString("source_from")); // source_from
                destinationTo.add(query.getString("destination_to")); // destination_to
                departureDATE.add(query.getString("DATE(departure)")); // departure DATE
                departureTime.add(query.getString("TIME(departure)")); // departure TIMR
                arrivalDate.add(query.getString("DATE(arrival)"));// arrival DATE
                arrivalTime.add(query.getString("TIME(arrival)")); // arrival TIME
                flightClass.add(query.getString("class")); // class
            }
            bookings.add(passengerN);
            bookings.add(tickN);
            bookings.add(ticketC);
            bookings.add(seatNumber);
            bookings.add(sourceFrom);
            bookings.add(destinationTo);
            bookings.add(departureDATE);
            bookings.add(departureTime);
            bookings.add(arrivalDate);
            bookings.add(arrivalTime);
            bookings.add(flightClass);
            stm.close();
        }catch(Exception e) {
            Log.e("Error",e.getMessage());
        }
        return bookings;
    }

}
