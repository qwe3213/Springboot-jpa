package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    // Transactional이 readOnly = true일 경우에는 저장이 안되므로 다시 Transactional을 해줌

    public List<Item> findItem(){
       return itemRepository.findAll();
    }

    public Item findOne(Long itemid){
        return itemRepository.findOne(itemid);
    }

}