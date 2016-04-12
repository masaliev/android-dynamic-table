package com.github.masaliev.dinamictable;

/**
 * Created by mbt on 4/13/16.
 */
public class StoreItems {
    String serialNumber;
    String items;
    String brand;
    String price;
    String id;
    String status;


    public StoreItems()
    {

    }

    public StoreItems(String serialNumber, String items, String brand, String price, String id, String status)
    {
        this.serialNumber = serialNumber;
        this.items = items;
        this.brand = brand;
        this.price = price;
        this.id = id;
        this.status = status;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void setItems(String items)
    {
        this.items = items;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSerialNumber()
    {

        return serialNumber;
    }

    public String getItems()
    {
        return items;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getPrice()
    {
        return price;
    }

    public String getId()
    {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }
}
