package com.test.jpa.realTest.repository.mybatis;

import com.test.jpa.realTest.entity.Member;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;


    @Test
    public void mybatis_맴버조회(){
        Optional<Member> optMember = memberMapper.findById(36L);
        Member findMember = Optional.ofNullable(optMember.get()).get();

        System.out.println(findMember.getUsername());
    }

    @Test
    public void mybatis_전체조회(){
        List<Member> result = memberMapper.findByAll();

        for (Member member : result) {
            System.out.println(member.getUsername());
        }
    }
}