package hello.itemservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data//DTO로 사용시에는 사용하라
@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private int quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
