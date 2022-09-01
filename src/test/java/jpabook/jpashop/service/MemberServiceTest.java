package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//스프링에서 Transactional은 기본적으로 롤백을 함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false) //해당 어노테이션을 사용하기 싫다면 flush를 호출 하면 됨
    public void 회원가입() throws Exception{
            //given
            Member member = new Member();
            member.setName("kim");

            //when
            Long saveId = memberService.join(member);

            //then
            assertEquals(member, memberRepository.findOne(saveId));

    }
    
    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        //@Test에 (expected = IllegalStateException.class) 설정을 주면 try을 작성 하지 않아도 됨
        try{
            memberService.join(member1);//예외가 발생해야 한다.
        } catch (IllegalStateException e){
            return;
        }

        
        //then
        fail("예외가 발생해야 한다.");
        
    }

}