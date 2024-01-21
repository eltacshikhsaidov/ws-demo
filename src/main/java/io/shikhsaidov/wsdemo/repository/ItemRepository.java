package io.shikhsaidov.wsdemo.repository;

import io.shikhsaidov.wsdemo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
