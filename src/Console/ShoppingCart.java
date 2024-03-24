package Console;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> listOfProducts;

    public void addItem(Product product){
        listOfProducts.add(product);
    }

    public void removeItem(Product product){
        listOfProducts.remove(product);
    }

}
