package com.kgisl.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

public class BusDAO {
  
    public static Connection connectionGet() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root","");
    }
    public void insert(List<Bus> list) throws IllegalStateException, FileNotFoundException, SQLException
    {
       
        PreparedStatement pst = connectionGet().prepareStatement("insert into bus_details values(?,?,?,?,?)");
         
        for(Bus li:list){
            pst.setDate(1, li.getAvaildate());
            pst.setInt(2,li.getBusNo());
            pst.setString(3,li.getBusName());
            pst.setInt(4,li.getAvailableSeats());
            pst.setString(5, li.getCustome_name());

            pst.executeUpdate();
        }

    }
 //customer with most bookings....
    public void maximumBooking(List<Bus> li){
        Map<String, Long> customerBookingCounts = li.stream()
        .collect(Collectors.groupingBy(Bus::getCustome_name, Collectors.counting()));

    Optional<Map.Entry<String, Long>> maxBookingCustomer = customerBookingCounts.entrySet().stream()
        .max(Map.Entry.comparingByValue());

    if (maxBookingCustomer.isPresent()) {
        System.out.println("Customer with most bookings: " +
            maxBookingCustomer.get().getKey() + " with " + maxBookingCustomer.get().getValue() + " bookings.");
    } else {
        System.out.println("No bookings found.");
    }

    }
    //listing all buses for specific customer..

    public void listBusesByCustomer(List<Bus> list, String customerName) {
        List<Bus> customerBuses = list.stream()
            .filter(bus -> bus.getCustome_name().equals(customerName))
            .collect(Collectors.toList());

        if (customerBuses.isEmpty()) {
            System.out.println("No bookings found for customer: " + customerName);
        } else {
            customerBuses.forEach(System.out::println);
        }
    }

    public void countTotalAvailableSeats(List<Bus> list) {
        Map<String, Integer> seatsPerBus = list.stream()
            .collect(Collectors.groupingBy(Bus::getBusName, 
                Collectors.summingInt(Bus::getAvailableSeats)));

        seatsPerBus.forEach((busName, totalSeats) -> 
            System.out.println("Bus: " + busName + " - Total Available Seats: " + totalSeats));
    }

    public List<Bus> read() throws SQLException{
          PreparedStatement pst=connectionGet().prepareStatement("select * from bus_details");
          ResultSet rs = pst.executeQuery();

          List<Bus> li = new ArrayList<>();
          while(rs.next()){
            Bus b =new Bus();
            b.setAvaildate(rs.getDate(1));
            b.setBusNo(rs.getInt(2));
            b.setBusName(rs.getString(3));
            b.setAvailableSeats(rs.getInt(4));
            b.setCustome_name(rs.getString(5));
            li.add(b);
          }
          return li;
    }


    
    public static void main(String[] args) throws IllegalStateException, FileNotFoundException, SQLException, ParseException {
        String fileNAme="bus.csv";
        List<Bus> list = new CsvToBeanBuilder(new FileReader(fileNAme)).withType(Bus.class).build().parse();
        BusDAO ob = new BusDAO();
        //ob.insert(list);
       
        List<Bus> readed = ob.read();


        // the person who booked max times;
        ob.maximumBooking(readed);

        ob.listBusesByCustomer(readed, "Alice"); 
      ob.countTotalAvailableSeats(list); 

      
    }
   
    }

