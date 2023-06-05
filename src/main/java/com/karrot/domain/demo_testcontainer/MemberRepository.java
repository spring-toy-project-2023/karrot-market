package com.karrot.domain.demo_testcontainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByName(String name);

    public List<Member> findAll();

}
