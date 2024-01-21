package io.shikhsaidov.wsdemo.service;

import io.shikhsaidov.wsdemo.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    void removeItem(long docNo);

    void restoreAllRemovedItems();
}
