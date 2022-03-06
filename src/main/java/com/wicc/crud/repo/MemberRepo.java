package com.wicc.crud.repo;


import com.wicc.crud.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepo extends JpaRepository<Member,Integer> {

}
