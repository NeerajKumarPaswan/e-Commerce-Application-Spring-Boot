package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Item;
import com.ty.shoppingcart.repository.ItemRepository;
@Repository
public class ItemDao {
	
	@Autowired
	ItemRepository itemRepository;

	public Item saveItem(Item item) {

		return itemRepository.save(item);
	}

	public Item getItem(int id) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Item> getAllItem() {
		return itemRepository.findAll();
	}

	public Item updateItem(int id, Item item) {
		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			if (item.getQuantity() == 0) {
				item.setStatus("unavailable");
			} else {
				item.setStatus("available");
			}
			return itemRepository.save(item);
		} else {
			return null;
		}
	}

	public boolean deleteItem(int id) {
		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			itemRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}
}
