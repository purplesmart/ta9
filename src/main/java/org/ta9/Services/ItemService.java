package org.ta9.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta9.Entities.Item;
import org.ta9.Repositories.ItemRepository;
import java.util.*;

@Service
public class ItemService {

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found"));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item newItem) {
        Item existingItem = getItemById(id);
        existingItem.setName(newItem.getName());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> searchItemsByName(String searchQuery) {
        return itemRepository.findByNameContainingIgnoreCase(searchQuery);
    }
}
