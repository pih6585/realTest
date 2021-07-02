package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.repository.query.MemberRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryQuery {


}
