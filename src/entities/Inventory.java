package entities;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class Inventory {
    private BigInteger id;
    private List<String> items;

    public Inventory(List<String> items) {
        this.id = new BigInteger(128, new SecureRandom());;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
