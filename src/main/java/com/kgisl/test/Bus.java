package com.kgisl.test;

import java.sql.Date;
import java.time.LocalDate;

import com.opencsv.bean.CsvBindByPosition;

public class Bus {
    
    

    @CsvBindByPosition(position = 0)
    private Date Availdate;

    @CsvBindByPosition(position = 1)
    private int busNo;

    @CsvBindByPosition(position = 2)
    private String busName;

    @CsvBindByPosition(position = 3)
    private int availableSeats;

    @CsvBindByPosition(position = 4)
    private String custome_name;

    public Date getAvaildate() {
        return Availdate;
    }

    public void setAvaildate(Date availdate) {
        Availdate = availdate;
    }

    public int getBusNo() {
        return busNo;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getCustome_name() {
        return custome_name;
    }

    public void setCustome_name(String custome_name) {
        this.custome_name = custome_name;
    }

    @Override
    public String toString() {
        return "Bus [Availdate=" + Availdate + ", busNo=" + busNo + ", busName=" + busName + ", availableSeats="
                + availableSeats + ", custome_name=" + custome_name + "]";
    }
 

    
    

    

}
