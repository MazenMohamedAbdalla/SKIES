package com.example.skies;

import android.content.Intent;
import android.util.Log;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {
    private int id;
    private String name;
    private String email;
    private String pass;
    private String confirmPass;

    public void setId(int id){this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setEmail(String email){ this.email = email;}
    public void setPass(String pass){ this.pass = pass;}
    public void setConfirmPass(String confirmPass){this.confirmPass = confirmPass;}

    public int getId() {return id;}
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public String getConfirmPass() { return confirmPass; }

    DB_Connection connect = new DB_Connection();
    Connection myDb = connect.Connect_DB("skies");
    flights flight = new flights();
    tickets ticket = new tickets();
    bankAccountInfo account = new bankAccountInfo();

    // 1) logIn
    public boolean logIn(String email, String pass){
        setEmail(email);
        setPass(pass);
        boolean loggedIn = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT email,pass FROM users WHERE email = '" + getEmail() + "' AND pass = '" + getPass() + "'");
            if (query.next()){
                setId(query.getInt("id"));
                stm.close();
                loggedIn = true;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return loggedIn;
    }

    // 2) signUp
    public boolean signUp(String name, String email, String pass, String confirmPass){
        setName(name);
        setEmail(email);
        setPass(pass);
        setConfirmPass(confirmPass);
        boolean signedUp = false;

        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("INSERT INTO users (name,email,pass,con_pass) VALUES ('"+getName()+"','"+getEmail()+"','"+getPass()+"','"+getConfirmPass()+"')");
            stm.close();
            signedUp = true;
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return signedUp;
    }

    // 3) recoverPassword
    public boolean recoverPassword(String email){
        setEmail(email);
        String pass = null;
        boolean found = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT pass FROM users WHERE email = '"+getEmail()+"'");
            if (query.next()){
                pass = query.getString("pass");
                setPass(pass);
                found = true;
                stm.close();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return found;
    }

    // 4) addBankAccount
    public boolean addBankAccount(String cardNumber,String cardHolder,String expDate,String cvv){
        account.setCardNumber(cardNumber);
        account.setCardHolder(cardHolder);
        account.setExpDate(expDate);
        account.setCvv(cvv);
        boolean added = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '"+account.getCardNumber()+"' AND card_holder = '"+account.getCardHolder()+"' AND expiry_date = '"+account.getExpDate()+"' AND cvv = '"+account.getCvv()+"';");
            if(query.next()){
                account.setBalance(query.getInt("balance"));
                ResultSet query2 = stm.executeQuery("INSERT INTO skies.bank_accounts VALUES ('"+account.getCardNumber()+"','"+getId()+"','"+account.getBalance()+"');");
                stm.close();
                added = true;
            }
        }catch(Exception e){
            Log.e("Error",e.getMessage());
        }
        return added;
    }

    // 5) deleteBankAccount
    public boolean deleteBankAccount(String cardNumber, String cvv){
        boolean deleted = false;
        account.setCardNumber(cardNumber);
        account.setCvv(cvv);
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '"+account.getCardNumber()+"' AND cvv = '"+account.getCvv()+"';");
            if(query.next()){
                ResultSet query2 = stm.executeQuery("DELETE FROM skies.bank_accounts WHERE account_number = '"+account.getCardNumber()+"';");
                stm.close();
                deleted = true;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return deleted;
    }

    // 6) updateBankAccount
    public boolean updateBankAccount(String oldCardNumber,String newCardNumber,String cardHolder,String expDate,String cvv){
        account.setCardNumber(newCardNumber);
        account.setCardHolder(cardHolder);
        account.setExpDate(expDate);
        account.setCvv(cvv);
        boolean updated = false;
        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * FROM bank.credit_cards\n" +
                    "WHERE card_number = '"+account.getCardNumber()+"' AND card_holder = '"+account.getCardHolder()+"' AND expiry_date = '"+account.getExpDate()+"' AND cvv = '"+account.getCvv()+"';");
            if(query.next()){
                account.setBalance(query.getInt("balance"));
                ResultSet query2 = stm.executeQuery("UPDATE skies.bank_accounts SET account_number = '"+account.getCardNumber()+"', account_balance = '"+account.getBalance()+"' WHERE account_number = '"+oldCardNumber+"';");
                stm.close();
                updated = true;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return updated;
    }

    // 7) searchFlight
    public boolean searchFlight(String source,String destination,String departureDate,String arrivalDate,String flightClass){
        flight.setFrom(source);
        flight.setTo(destination);
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        flight.seteClass(flightClass);

        boolean found = false;

        try {
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT * flights JOIN class ON flights.flight_number = class.flight_number WHERE source_from = '"+flight.getFrom()+"' " +
                    "AND destination_to = '"+flight.getTo()+"' AND departure_date = '"+flight.getDepartureDate()+"' " +
                    "AND arrival_date = '"+flight.getArrivalDate()+"' AND flight_class = '"+flight.geteClass()+"'");
            while(query.next()){
                flight.setFlightNumber(query.getString("flight_number"));
                flight.seteClass(query.getString("flight_class"));
                flight.setFrom(query.getString("source_from"));
                flight.setTo(query.getString("destination_to"));
                flight.setDepartureDate(query.getString("departure_date"));
                flight.setArrivalDate(query.getString("arrival_date"));
                flight.setCost(query.getInt("cost"));
                flight.setDepartureTime(query.getString("departure_time"));
                flight.setArrivalTime(query.getString("arrival_time"));
                flight.setDuration(query.getString("duration"));
                found = true;
            }
            stm.close();
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return found;
    }

    // 8) myBookings
    public boolean myBookings(){
        boolean hasBookings = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT tickets.flight_number,passenger_name,source,destination,class,departure_date,departure_time,arrival_time\n" +
                    "FROM tickets JOIN flights ON tickets.flight_number = flights.flight_number;");
            while (query.next()){
                ticket.setFlightNumber(query.getString("tickets.flight_number"));
                ticket.setPassengerName(query.getString("passenger_name"));
                ticket.setSource(query.getString("source"));
                ticket.setDestination(query.getString("destination"));
                ticket.setTicketClass(query.getString("class"));
                flight.setDepartureTime(query.getString("departure_time"));
                flight.setArrivalTime(query.getString("arrival_time"));
                flight.setDepartureDate(query.getString("departure_date"));
                hasBookings = true;
            }
            stm.close();
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return hasBookings;
    }

    // 9) flightStatus
    public boolean checkFlightStatus(String flightNumber){
        flight.setFlightNumber(flightNumber);
        boolean found = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT flights.flight_number,current_status,source_from,departure_date,departure_time,destination_to,arrival_date,arrival_time\n" +
                    "FROM flights JOIN flight_status ON flights.flight_number = flight_status.flight_number\n" +
                    "WHERE flights.flight_number = '"+flight.getFlightNumber()+"';");
            if(query.next()){
                flight.setStatus(query.getString("current_status"));

                flight.setFrom(query.getString("source_from"));
                flight.setDepartureTime(query.getString("departure_time"));
                flight.setDepartureDate(query.getString("departure_date"));

                flight.setTo(query.getString("destination_to"));
                flight.setArrivalTime(query.getString("arrival_time"));
                flight.setArrivalDate(query.getString("arrival_date"));

                stm.close();
                found = true;
            }

        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return found;
    }

    // 10) bookFlight
    public boolean bookFlight(String flightNumber,String passengerName,String cardNumber){
        flight.setFlightNumber(flightNumber);
        ticket.setPassengerName(passengerName);
        account.setCardNumber(cardNumber);
        // 1 - query flights to check flightNumber seats and cost
        // 2 - query from bankAcc to check balance
        // 3 - Insert flightNumber,name,source,destination,class,user_id
        // 4 - booked
        boolean booked = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet checkQuery = stm.executeQuery("SELECT *\n" +
                    "FROM skies.flights JOIN skies.class ON flights.flight_number = class.flight_number\n" +
                    "WHERE flights.flight_number = '"+flight.getFlightNumber()+"';");
            ResultSet balanceQuery = stm.executeQuery("SELECT account_balance FROM skies.bank_accounts\n" +
                    "WHERE account_number = '"+account.getCardNumber()+"';");
            if(checkQuery.next() && balanceQuery.next()) {
                // flight cost & seats
                flight.setCost(checkQuery.getInt("cost"));
                flight.setSeats(checkQuery.getInt("seats"));
                // account balance
                account.setBalance(balanceQuery.getInt("account_balance"));
                // values of flight cost & seats , account balance
                int seats = flight.getSeats();
                int cost = flight.getCost();
                int balance = account.getBalance();
                if(seats > 0 && balance >= cost){
                    // flight source,destination & class
                    flight.setFrom(checkQuery.getString("source_from"));
                    flight.setTo(checkQuery.getString("destination_to"));
                    flight.seteClass(checkQuery.getString("flight_class"));
                    ResultSet insertQuery = stm.executeQuery("INSERT INTO skies.tickets (flight_number,passenger_name,source,destination,class,user_id,cost) values ('"+flight.getFlightNumber()+"','"+ticket.getPassengerName()+"','"+flight.getFrom()+"','"+flight.getTo()+"','"+flight.geteClass()+"','"+getId()+"','"+flight.getCost()+"');");
                    seats = seats - 1;
                    balance = balance - cost;
                    // update seats & balance
                    ResultSet updateBalance = stm.executeQuery("UPDATE skies.bank_accounts SET account_balance = '"+balance+"' WHERE account_number = '"+account.getCardNumber()+"';");
                    ResultSet updateSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+seats+"' WHERE flight_number = '"+flight.getFlightNumber()+"';");
                    booked = true;
                }
            }

        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return booked;
    }

    // 11) cancelFlight
    public boolean cancelFlight(String flightNumber,String passengerName){
        flight.setFlightNumber(flightNumber);
        ticket.setPassengerName(passengerName);
        // 1) query to get flight from tickets
        // 2) query to add seats of flight by 1
        // 3) query to refund by 60% the payment
        boolean canceled = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet ticketQuery = stm.executeQuery("SELECT * FROM skies.tickets WHERE flight_number = '"+flight.getFlightNumber()+"' AND passenger_name = '"+ticket.getPassengerName()+"';");
            ResultSet flightQuery = stm.executeQuery("SELECT * FROM skies.flights WHERE flight_number = '"+flight.getFlightNumber()+"';");
            ResultSet bankQuery = stm.executeQuery("SELECT * FROM skies.bank_accounts WHERE account_number = '"+account.getCardNumber()+"'");
            if(ticketQuery.next()){
                ticket.setPsnNumber(ticketQuery.getInt("psn_id"));
                ticket.setTicketCost(ticketQuery.getInt("cost"));
                int seats = flightQuery.getInt("seats");
                int balance = bankQuery.getInt("account_balance");
                int paid = ticketQuery.getInt("cost");
                ResultSet cancelQuery = stm.executeQuery("Delete FROM skies.tickets WHERE psn_id = '"+ticket.getPsnNumber()+"';");
                seats = seats + 1; // + seats
                balance = balance + (paid*(60*100)); // + balance
                // update
                ResultSet updateBalance = stm.executeQuery("UPDATE skies.bank_accounts SET account_balance = '"+balance+"' WHERE account_number = '"+account.getCardNumber()+"';");
                ResultSet updateSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+seats+"' WHERE flight_number = '"+flight.getFlightNumber()+"';");
                canceled = true;
            }
            ResultSet seatsQuery = null;
            ResultSet balanceQuery = null;
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return canceled;
    }

    // 12) editTicketClass
    public boolean editTicketClass(String newClass,String flightNumber){
        flight.setFlightNumber(flightNumber);
        flight.seteClass(newClass);
        boolean done = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet bookedTicket = stm.executeQuery("SELECT psn_id,tickets.source,tickets.destination,departure_date,arrival_date,tickets.cost,seats FROM skies.tickets \n" +
                    "JOIN skies.flights ON tickets.flight_number = flights.flight_number\n" +
                    "WHERE tickets.flight_number = '"+flight.getFlightNumber()+"';");
            ResultSet newFlight = stm.executeQuery("SELECT * flights JOIN class ON flights.flight_number = class.flight_number WHERE source_from = '"+bookedTicket.getString("tickets.source")+"' " +
                    "AND destination_to = '"+bookedTicket.getString("tickets.destination")+"' AND departure_date = '"+bookedTicket.getString("departure_date")+"' " +
                    "AND arrival_date = '"+bookedTicket.getString("arrival_date")+"' AND flight_class = '"+flight.geteClass()+"'");
            ResultSet bankQuery = stm.executeQuery("SELECT * FROM skies.bank_accounts WHERE account_number = '"+account.getCardNumber()+"'"); // ****
            if(newFlight.next()){
                // old flight increase seats by 1
                int oldFlightSeats = bookedTicket.getInt("seats");
                oldFlightSeats = oldFlightSeats +1;
                // new flight decrease seats by 1
                int newFlightSeats = newFlight.getInt("seats");
                newFlightSeats = newFlightSeats - 1;
                // ticket cost
                // add 20% to cost
                int newCost = bookedTicket.getInt("tickets.cost") + (bookedTicket.getInt("tickets.cost")*(20*100));
                // balance
                int balance = bankQuery.getInt("account_balance");
                if(balance >= newCost){
                    balance = balance - newCost;
                    ResultSet updateTicket = stm.executeQuery("UPDATE skies.tickets SET flight_number = '"+newFlight.getString("flight_number")+"',class = '"+newFlight.getString("flight_class")+"'  WHERE psn_id = '"+bookedTicket.getString("psn_id")+"';");
                    ResultSet incOldFlightSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+oldFlightSeats+"' WHERE flight_number = '"+flight.getFlightNumber()+"';"); // bookedTicket.getString(tickets.flight_number)  , oldFlightSeats
                    ResultSet decNewFlightSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+newFlightSeats+"' WHERE flight_number = '"+newFlight.getString("flights.flight_number")+"';"); // newFlight.getString("flight_number") , newFlightSeats
                    ResultSet updateBalance = stm.executeQuery("UPDATE skies.bank_accounts SET account_balance = '"+balance+"' WHERE account_number = '"+account.getCardNumber()+"';");
                    done = true;
                }
                // update payment from bank balance
            }

        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return done;
    }

    // 13) editTicketDates
    public boolean editTicketDates(String departureDate,String arrivalDate,String flightNumber){
        flight.setFlightNumber(flightNumber);
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        boolean done = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet bookedTicket = stm.executeQuery("SELECT psn_id,tickets.source,tickets.destination,departure_date,arrival_date,tickets.cost,seats,tickets.class FROM skies.tickets \n" +
                    "JOIN skies.flights ON tickets.flight_number = flights.flight_number\n" +
                    "WHERE tickets.flight_number = '"+flight.getFlightNumber()+"';");
            ResultSet newFlight = stm.executeQuery("SELECT * flights JOIN class ON flights.flight_number = class.flight_number WHERE source_from = '"+bookedTicket.getString("tickets.source")+"' " +
                    "AND destination_to = '"+bookedTicket.getString("tickets.destination")+"' AND departure_date = '"+ flight.getDepartureDate()+"' " +
                    "AND arrival_date = '"+flight.getArrivalDate()+"' AND flight_class = '"+bookedTicket.getString("tickets.class")+"'");
            ResultSet bankQuery = stm.executeQuery("SELECT * FROM skies.bank_accounts WHERE account_number = '"+account.getCardNumber()+"'"); // ****
            if(newFlight.next()){
                // old flight increase seats by 1
                int oldFlightSeats = bookedTicket.getInt("seats");
                oldFlightSeats = oldFlightSeats +1;
                // new flight decrease seats by 1
                int newFlightSeats = newFlight.getInt("seats");
                newFlightSeats = newFlightSeats - 1;
                // ticket cost
                // add 20% to cost
                int newCost = bookedTicket.getInt("tickets.cost") + (bookedTicket.getInt("tickets.cost")*(20*100));
                // balance
                int balance = bankQuery.getInt("account_balance");
                if(balance >= newCost){
                    balance = balance - newCost;
                    ResultSet updateTicket = stm.executeQuery("UPDATE skies.tickets SET flight_number = '"+newFlight.getString("flight_number")+"',departure_date = '"+newFlight.getString("departure_date")+"',arrival_date = '"+newFlight.getString("arrival_date ")+"'  WHERE psn_id = '"+bookedTicket.getString("psn_id")+"';");
                    ResultSet incOldFlightSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+oldFlightSeats+"' WHERE flight_number = '"+flight.getFlightNumber()+"';"); // bookedTicket.getString(tickets.flight_number)  , oldFlightSeats
                    ResultSet decNewFlightSeats = stm.executeQuery("UPDATE skies.flights SET seats = '"+newFlightSeats+"' WHERE flight_number = '"+newFlight.getString("flights.flight_number")+"';"); // newFlight.getString("flight_number") , newFlightSeats
                    ResultSet updateBalance = stm.executeQuery("UPDATE skies.bank_accounts SET account_balance = '"+balance+"' WHERE account_number = '"+account.getCardNumber()+"';");
                    done = true;
                }
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return done;
    }

    // 14) changePassword
    public boolean changePassword(String email, String oldPass, String newPass){
        boolean changed = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT email,pass FROM users WHERE email = '" + email + "' AND pass = '" + oldPass + "'");
            if (query.next()){
                ResultSet upDate = stm.executeQuery("UPDATE skies.users SET pass = '"+newPass+"' WHERE email = '"+email+"';");
                stm.close();
                changed = true;
            }

        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return changed;
    }

    // 15) changeEmail
    public boolean changeEmail(String email,String newEmail, String pass){
        boolean changed = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT email,pass FROM users WHERE email = '" + email + "' AND pass = '" + pass + "'");
            if (query.next()){
                ResultSet upDate = stm.executeQuery("UPDATE skies.users SET email = '"+newEmail+"' WHERE email = '"+email+"';");
                stm.close();
                changed = true;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return changed;
    }

    // 16) changeName
    public boolean changeName(String newName,String email, String pass){
        boolean changed = false;
        try{
            Statement stm = myDb.createStatement();
            ResultSet query = stm.executeQuery("SELECT email,pass FROM users WHERE email = '" + email + "' AND pass = '" + pass + "'");
            if (query.next()){
                ResultSet upDate = stm.executeQuery("UPDATE skies.users SET name = '"+newName+"' WHERE email = '"+email+"';");
                stm.close();
                changed = true;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return changed;
    }
}
