package com.example.bookmanagementsample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookmanagementsample.repository.StoreRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.bookmanagementsample.model.Book;
import com.example.bookmanagementsample.model.Store;
//Optional型を使うためのimport
//Optionalってなんだっけ？→値が存在するかどうか確認し、存在したらその値をなかったら
//Nullの代わりに空のOptionalオブジェクトを返す　NullのエラーNullPointexceptionを防ぐ
import java.util.Optional;
import java.util.Set;
//トランザクション
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    // 書店情報の全件取得
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    // 書店情報のID検索
    public Optional<Store> findStoreById(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public void saveStoreWithBooks(Store store, Set<Book> books) {
        store.setBooks(books);
        storeRepository.save(store); // 書店と書籍の関連が保存されます
    }
    // 書店と書籍の更新処理
    @Transactional
    public void updateStoreWithBooks(Store updatedStore, Set<Book> updatedBooks) {
        // 既存の書店を取得
        Store existingStore = storeRepository.findById(updatedStore.getId())
            .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        // 書店のデータ(名前)を更新
        existingStore.setName(updatedStore.getName());
        // 書店はnameだけだが
        // 他のプロパティもある場合は必要に応じて更新する

        // 書籍のセットを更新
        existingStore.setBooks(updatedBooks);

        // 書店を保存
        storeRepository.save(existingStore);
    }
}
