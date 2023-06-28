import java.util.Comparator;

public class Hotel {
    String name = "";
    int stars = 0;
    int price = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String print() {
        return String.format("%-15s", name) + String.format("%-15s", price) + String.format("%-15s", stars);
    }

    public Hotel(String name, int stars, int price) {
        this.name = name;
        this.stars = stars;
        this.price = price;
    }

    static Comparator<Hotel> STAR_NAME = new Comparator<Hotel>() {
        @Override
        public int compare(Hotel s1, Hotel s2) {
            if (s1.getStars() == s2.getStars()) {
                return s1.getName().compareTo(s2.getName());
            } else {
                return s2.getStars() - s1.getStars();
            }
        }
    };

    static Comparator<Hotel> PRICE = new Comparator<Hotel>() {
        @Override
        public int compare(Hotel s1, Hotel s2) {
            return s1.getPrice() - s2.getPrice();
        }

    };

    @Override
    public String toString() {
        String result =  "Name: " + name + "            Stars: " +  stars + "            Price:" +  price;
        System.out.println(result);
        return result;
    }
}