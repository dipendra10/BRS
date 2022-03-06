package com.wicc.crud.repo;

import com.wicc.crud.entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionRepo extends JpaRepository<BookTransaction,Integer> {

}
