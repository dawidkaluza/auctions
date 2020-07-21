package eu.horyzont.auctions.modules.item;

import javax.persistence.*;

@Entity(name = "item_image")
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    protected ItemImage() {
    }

    public ItemImage(String fileName, Item item) {
        this.fileName = fileName;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemImage image = (ItemImage) o;
        return id.equals(image.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
