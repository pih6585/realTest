package com.test.jpa.realTest.repository.mybatis;

import com.test.jpa.realTest.entity.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberMapper {

    @Autowired
    private SqlSession sqlSessionTemplate;

    public Optional<Member> findById(Long id){
        return Optional.ofNullable(sqlSessionTemplate.selectOne("com.test.jpa.realTest.repository.mybatis.MemberMapper.findById", id));
    }

    public List<Member> findByAll(){
        return sqlSessionTemplate.selectList("com.test.jpa.realTest.repository.mybatis.MemberMapper.findByAll");
    }

}
