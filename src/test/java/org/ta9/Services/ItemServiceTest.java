package org.ta9.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.ta9.Entities.Item;
import org.ta9.Repositories.ItemRepository;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemServiceTest {

    @MockBean
    ItemRepository itemRepository;
    ItemService itemService;

    @BeforeAll
    public void setUp() throws Exception {
        itemRepository = Mockito.mock(ItemRepository.class);
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void getAllItems() {
        Mockito.when(itemRepository.findAll())
                .thenReturn(List.of(new Item("One"), new Item("Two")));
        itemService.getAllItems();
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void getItemById() {
        long itemId = 3345;
        Mockito.when(itemRepository.findById(itemId))
                .thenReturn(Optional.of(new Item("One")));
        itemService.getItemById(itemId);
        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    public void createItem() {
        Item item = new Item("ASD");
        itemService.createItem(item);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void updateItem() {
        Item item = new Item("ASD");
        Mockito.when(itemRepository.findById(item.getId()))
                .thenReturn(Optional.of(item));
        item.setName("TRF");
        itemService.updateItem(item.getId(),item);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void deleteItem() {
        long itemId = 4343;
        itemService.deleteItem(itemId);
        verify(itemRepository, times(1)).deleteById(itemId);
    }

    @Test
    public void searchItemsByName() {
        String itemName = "Grdf";
        Mockito.when(itemRepository.findByNameContainingIgnoreCase(itemName))
                .thenReturn(List.of(new Item("One"), new Item("Two")));
        List<Item> items =  itemService.searchItemsByName(itemName);
        verify(itemRepository, times(1)).findByNameContainingIgnoreCase(itemName);
        Assert.isTrue(items.size() == 2,"Items count isn't compatible");
    }
}