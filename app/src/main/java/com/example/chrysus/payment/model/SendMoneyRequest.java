package com.example.chrysus.payment.model;

public class SendMoneyRequest {
    private String receiverPhoneNumber;
    private String senderUid;
    private Integer amount;
    private static String url = "http://198.12.106.137:5000/payment/pay";

    public static String getUrl() {
        return url;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public SendMoneyRequest(String receiverPhoneNumber, String senderUid, Integer amount) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderUid = senderUid;
        this.amount = amount;
    }

}
