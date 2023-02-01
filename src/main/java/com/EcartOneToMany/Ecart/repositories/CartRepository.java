package com.EcartOneToMany.Ecart.repositories;

import com.EcartOneToMany.Ecart.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity , Long> {


}
