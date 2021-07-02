package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.dto.QMemberDto;
import com.test.jpa.realTest.entity.QMember;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryQuery {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MemberDto findByOneByDto(Long id) {
        MemberDto memberDto = queryFactory
                .select(new QMemberDto(member.id,member.username, member.address.city, member.address.street, member.address.zipcode))
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
        return memberDto;
    }

    @Override
    public List<MemberDto> findByAllByDto() {
        List<MemberDto> memberDtoList = queryFactory
                .select(new QMemberDto(member.id,member.username, member.address.city, member.address.street, member.address.zipcode))
                .from(member)
                .fetch();
        return memberDtoList;
    }
}
