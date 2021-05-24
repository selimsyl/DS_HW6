import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable, Comparable<Product> {
  private String id;
  private String name;
  private String category;
  private Integer price;
  private Integer discountedPrice;
  private String description;
  private Object trader;

  public Product(String id, String name, String category, Integer price, Integer discountedPrice, String description, Object trader) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
    this.discountedPrice = discountedPrice;
    this.description = description;
    this.trader = trader;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getDiscountedPrice() {
    return discountedPrice;
  }

  public void setDiscountedPrice(Integer discountedPrice) {
    this.discountedPrice = discountedPrice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Object getTrader() {
    return trader;
  }

  public void setTrader(Object trader) {
    this.trader = trader;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id.equals(product.id) &&
            name.equals(product.name) &&
            category.equals(product.category) &&
            price.equals(product.price) &&
            discountedPrice.equals(product.discountedPrice) &&
            description.equals(product.description) &&
            trader.equals(product.trader);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, price, discountedPrice, description, trader);
  }

  @Override
  public int compareTo(Product o) {
    return this.name.compareTo(((Product)o).getName());
  }
}
