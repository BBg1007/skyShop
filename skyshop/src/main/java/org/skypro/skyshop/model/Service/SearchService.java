package org.skypro.skyshop.model.Service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public TreeSet<SearchResult> search(String search) {

        return storageService.toSearchable().stream()
                .filter(searchable -> searchable.searchTerm().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));

    }
}
