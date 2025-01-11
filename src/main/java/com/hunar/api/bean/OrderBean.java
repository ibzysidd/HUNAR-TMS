package com.hunar.api.bean;

import com.hunar.api.generic.bean.GenericBean;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class OrderBean extends GenericBean {

    private int orderId;

    private String orderNo;

    private LocalDate bookingDate;

    private LocalDate deliveryDate;

    private String comments;

    private String type; // pant, shirt etc

    private double amount;

    private double totalAmt;


    private int quantity;
    
    private String dart_point;

    private String waist_length;

    private  String length_of_shirt;

    private String upper_bust;

    private String bust;

    private String belly;

    private String waist;

    private String hip;

    private String shoulders;

    private String sleeves_length_width;


    private String arm_hole;

    private String biceps;

    private String length_of_pant;

    private String waist_of_pants;


    private String thighs;

    private String calfs;

    private String knees;

    private String round_bottom;

    private int idAddress;

    private int idCustomer;

    private  String orderStatus;

//    private MultipartFile image;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDart_point() {
        return dart_point;
    }

    public void setDart_point(String dart_point) {
        this.dart_point = dart_point;
    }

    public String getWaist_length() {
        return waist_length;
    }

    public void setWaist_length(String waist_length) {
        this.waist_length = waist_length;
    }

    public String getLength_of_shirt() {
        return length_of_shirt;
    }

    public void setLength_of_shirt(String length_of_shirt) {
        this.length_of_shirt = length_of_shirt;
    }

    public String getUpper_bust() {
        return upper_bust;
    }

    public void setUpper_bust(String upper_bust) {
        this.upper_bust = upper_bust;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getBelly() {
        return belly;
    }

    public void setBelly(String belly) {
        this.belly = belly;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getShoulders() {
        return shoulders;
    }

    public void setShoulders(String shoulders) {
        this.shoulders = shoulders;
    }

    public String getSleeves_length_width() {
        return sleeves_length_width;
    }

    public void setSleeves_length_width(String sleeves_length_width) {
        this.sleeves_length_width = sleeves_length_width;
    }

    public String getArm_hole() {
        return arm_hole;
    }

    public void setArm_hole(String arm_hole) {
        this.arm_hole = arm_hole;
    }

    public String getBiceps() {
        return biceps;
    }

    public void setBiceps(String biceps) {
        this.biceps = biceps;
    }

    public String getLength_of_pant() {
        return length_of_pant;
    }

    public void setLength_of_pant(String length_of_pant) {
        this.length_of_pant = length_of_pant;
    }

    public String getWaist_of_pants() {
        return waist_of_pants;
    }

    public void setWaist_of_pants(String waist_of_pants) {
        this.waist_of_pants = waist_of_pants;
    }

    public String getThighs() {
        return thighs;
    }

    public void setThighs(String thighs) {
        this.thighs = thighs;
    }

    public String getCalfs() {
        return calfs;
    }

    public void setCalfs(String calfs) {
        this.calfs = calfs;
    }

    public String getKnees() {
        return knees;
    }

    public void setKnees(String knees) {
        this.knees = knees;
    }

    public String getRound_bottom() {
        return round_bottom;
    }

    public void setRound_bottom(String round_bottom) {
        this.round_bottom = round_bottom;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
