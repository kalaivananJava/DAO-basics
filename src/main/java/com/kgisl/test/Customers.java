package com.kgisl.test;
import java.util.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Customers {
    private int customer_id;
    private String customerName;
    private LocalDate bookingDate;
    private int totalSeats;
    private int busNo;

   public  void getUser(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter passenger name");
    customerName=sc.nextLine();
    System.out.println("Enter busNo");
    busNo=sc.nextInt();
    System.out.println("Enter date yyyy-mm-dd");
    String dateInput=sc.nextLine();
    bookingDate = LocalDate.parse(dateInput);
   }
   
     


    

    

    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
   


    

}
