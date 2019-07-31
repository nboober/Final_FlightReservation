package com.example.demo;

import javax.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String startLocation;

    private String endLocation;

    private int price;

    private int totalPrice;

    private String startDate;

    private String endDate;

    private String startTime;

    private String endTime;

    private boolean roundTrip;

    private boolean layover;

    private String flightClass;

    private int layoverDuration;

    private int flightCapacity;

    private int rewardPoints;

    private int discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Flight(){

    }

    public Flight(String startLocation, String endLocation, int price, int totalPrice, String startDate, String endDate, String startTime, String endTime, boolean roundTrip, boolean layover, String flightClass, int layoverDuration, int flightCapacity, int rewardPoints) {
        this.setStartLocation(startLocation);
        this.setEndLocation(endLocation);
        this.setPrice(price);
        this.setTotalPrice(totalPrice);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setRoundTrip(roundTrip);
        this.setLayover(layover);
        this.setFlightClass(flightClass);
        this.setLayoverDuration(layoverDuration);
        this.setFlightCapacity(flightCapacity);
        this.setRewardPoints(rewardPoints);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
