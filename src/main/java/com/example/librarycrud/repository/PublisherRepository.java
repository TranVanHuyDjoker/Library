package com.example.librarycrud.repository;


import com.example.librarycrud.model.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
//    Publisher findByName(String name);
}
