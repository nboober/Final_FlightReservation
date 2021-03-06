package com.example.demo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.NumberFormat;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String search;

    private double tax;

    private double totalForTax;

    private double totalCost;

    private double totalWithTax;

    @Max(220)
    private int quantity;

    private String seatType;

    private int seatNumber;

    private int seatTypeCapacity;

    private int flightCapacity;

    private String seatClass;

    private String startLocation;

    private String endLocation;

    private double price;

    private String startDate;

    private String endDate;

    private String startTime;

    private String endTime;

    private boolean roundTrip;

    private boolean layover;

    private int layoverDuration;

    private int rewardPoints;

    private boolean discount;

    private String operatingAirline;

    private String operatingAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Flight(){

    }

    public Flight(String operatingAirport, String operatingAirline, String startLocation, String endLocation, double price, String startDate, String endDate, String startTime, String endTime, boolean roundTrip, boolean layover, int flightCapacity, int rewardPoints, boolean discount) {
        this.setOperatingAirport(operatingAirport);
        this.setOperatingAirline(operatingAirline);
        this.setStartLocation(startLocation);
        this.setEndLocation(endLocation);
        this.setPrice(price);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setRoundTrip(roundTrip);
        this.setLayover(layover);
        this.setFlightCapacity(flightCapacity);
        this.setRewardPoints(rewardPoints);
        this.setDiscount(discount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTotalCost() {
        int total = 0;
        int additionalCosts = 0;



            if(this.getSeatType().equals("window")){
                additionalCosts += 5;
            }

            if(this.getSeatClass().equals("first")){
                additionalCosts += 100;
            }

            if(this.getSeatClass().equals("business")){
                additionalCosts += 50;
            }

            total += ((this.getPrice() + additionalCosts) * this.getQuantity());


        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalFormat = formatter.format(total);


        return totalFormat;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public boolean isLayover() {
        return layover;
    }

    public void setLayover(boolean layover) {
        this.layover = layover;
    }

    public int getLayoverDuration() {
        return layoverDuration;
    }

    public void setLayoverDuration(int layoverDuration) {
        this.layoverDuration = layoverDuration;
    }

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public void setFlightCapacity(int flightCapacity) {
        this.flightCapacity = flightCapacity;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOperatingAirline() {
        return operatingAirline;
    }

    public void setOperatingAirline(String operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public String getOperatingAirport() {
        return operatingAirport;
    }

    public void setOperatingAirport(String operatingAirport) {
        this.operatingAirport = operatingAirport;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getSeatTypeCapacity() {
        int seatTypeCapacity = 0;

        if(this.getSeatClass() == "first"){
            seatTypeCapacity = 20;
        }else if(this.getSeatClass() == "economy"){
            seatTypeCapacity = 100;
        }else if(this.getSeatClass() == "business"){
            seatTypeCapacity = 100;
        }

        return seatTypeCapacity;
    }

    public void setSeatTypeCapacity(int seatTypeCapacity) {
        this.seatTypeCapacity = seatTypeCapacity;
    }

    public double getTax() {

        double tax = .06 * this.getTotalForTax();

        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalForTax() {
        int totalForTax = 0;
        int additionalCosts = 0;



        if(this.getSeatType().equals("window")){
            additionalCosts += 5;
        }

        if(this.getSeatClass().equals("first")){
            additionalCosts += 100;
        }

        if(this.getSeatClass().equals("business")){
            additionalCosts += 50;
        }

        totalForTax += ((this.getPrice() + additionalCosts) * this.getQuantity());


        return totalForTax;
    }

    public void setTotalForTax(double totalForTax) {
        this.totalForTax = totalForTax;
    }

    public String getTotalWithTax() {

        double totalwithouttax = this.getTotalForTax();
        double tax = this.getTax();

        double total = (totalwithouttax + tax);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalFormat = formatter.format(total);


        return totalFormat;
    }

    public void setTotalWithTax(double totalWithTax) {
        this.totalWithTax = totalWithTax;
    }
}
