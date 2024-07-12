public class Dish{
    private String dishName;
    private double price;
    private String dishType;

    public Dish(){
        dishName = "";
        price = 0.00;
        dishType = "";

    }

    @Override
    public String toString() {
        return "Dish [dishName=" + dishName + ", dishType=" + dishType +  ", price=" + price + "]";
    }


    public Dish(String name, double price, String type){
        this.dishName=name;
        this.price=price;
        this.dishType=type;

    }

    public String getDishName() {
        return dishName;
    }


    public void setDishName(String dishName) {
        this.dishName = dishName;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public String getDishType() {
        return dishType;
    }


    public void setDishType(String dishType) {
        this.dishType = dishType;
    }


    public double getPrice(){
        return price;
    }

    public double calcPrice(int qty){
        return price*qty;
    }
}