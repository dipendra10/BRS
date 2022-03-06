package com.wicc.crud.repo;

import com.wicc.crud.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
