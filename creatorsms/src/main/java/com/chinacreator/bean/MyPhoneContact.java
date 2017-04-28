package com.chinacreator.bean;

/**
 * Created by Smile on 2017/4/3.
 */

public class MyPhoneContact {
    public String contactId;
    public String displayName;
    public String phoneNumber;
    public String photo_thumb_uri;

    public MyPhoneContact(){

    }
    public MyPhoneContact(String contactId, String displayName, String phoneNumber, String photo_thumb_uri) {
        this.contactId = contactId;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.photo_thumb_uri = photo_thumb_uri;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto_thumb_uri() {
        return photo_thumb_uri;
    }

    public void setPhoto_thumb_uri(String photo_thumb_uri) {
        this.photo_thumb_uri = photo_thumb_uri;
    }

    @Override
    public String toString() {
        return "MyPhoneContact{" +
                "contactId='" + contactId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photo_thumb_uri='" + photo_thumb_uri + '\'' +
                '}';
    }
}
