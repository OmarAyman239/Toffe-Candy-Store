
/**
 * Catalogue class which controls the items catalogue
 *
 *@author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.util.ArrayList;
import java.util.List;

public class Catalouge {
    private static List<Items> itemList;
    private List<Integer> StockList = FileManager.getStockList();

    public Catalouge() {
        itemList = new ArrayList<>();
        itemList.add(new Items("Chocolate", 20.0, StockList.get(0)));
        itemList.add(new Items("Ice Cream", 10.0, StockList.get(1)));
        itemList.add(new Items("Candy", 4.0, StockList.get(2)));
    }

    /**
     * Function that returns the item list
     * 
     * @return item List full of items in the catalog
     */
    public static List<Items> getItemList() {
        return itemList;
    }
}
