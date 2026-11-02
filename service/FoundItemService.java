
package com.campus.lostfound.service;

import com.campus.lostfound.model.FoundItem;
import com.campus.lostfound.repository.FoundItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FoundItemService {

    private final FoundItemRepository repository;

    public FoundItemService(FoundItemRepository repository) {
        this.repository = repository;
    }

    public List<FoundItem> searchItems(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                keyword, keyword);
    }

    public long countAll() {
        return repository.count();
    }

    public long countByType(String type) {
        return repository.countByType(type);
    }

    public long countOpen() {
        return repository.countByStatus(com.campus.lostfound.model.ItemStatus.OPEN);
    }

    public FoundItem saveItem(FoundItem item) {
        return repository.save(item);
    }

    public FoundItem getItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
