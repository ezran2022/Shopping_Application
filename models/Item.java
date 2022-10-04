package models;

public class Item {
    
    private String name;
    private double price;

    public Item(String name, double price){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name can not be null/blank");
        }
        if(price < 0){
            throw new IllegalArgumentException("Price can not be less than zero");
        }
        this.name = name;
        this.price = price;
    }
    public Item(Item source){
        this.name = source.name;
        this.price = source.price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name can not be null/blank");
        }
        this.name = name;
    }
    public void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("Price can not be less than zero");
        }
        this.price = price;
    }

    public String toString(){
        return name + ": $" + price + " ";
    }

    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Item)){
            return false;
        }

        Item item = (Item)obj;
        return this.name.equals(item.name) && this.price == item.price;
    }
}
