package com.example.librarycrud.repository;


import com.example.librarycrud.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m " +
            "       FROM Member m " +
            "       WHERE LOWER(m.name) LIKE LOWER(CONCAT('%',:keyword,'%'))" +
            "             OR LOWER(m.mail) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Member> searchMember(String keyword);

//    Page<Member> paging(@Param("keyword") String keyword, Pageable pageable);

}
