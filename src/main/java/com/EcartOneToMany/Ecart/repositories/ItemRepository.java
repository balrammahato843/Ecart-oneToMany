package com.EcartOneToMany.Ecart.repositories;

import com.EcartOneToMany.Ecart.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<ItemEntity, Long> {


}
