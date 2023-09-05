package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // test에서 롤백용도도 함
public class MemberserviceTest {

    @Autowired Memberservice memberservice;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Test
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setUsername("kim");
        // when
        Long saveId = memberservice.join(member);

        // then
        // em.flush()
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setUsername("kim");

        Member member2 = new Member();
        member2.setUsername("kim");

        // when
        memberservice.join(member1);
        memberservice.join(member2); // 예외가 발생해야 한다.

        // then
        fail("예외가 발생해야 한다!!");
    }
}