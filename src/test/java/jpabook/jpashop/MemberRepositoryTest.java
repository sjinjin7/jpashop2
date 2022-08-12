package jpabook.jpashop;

import org.assertj.core.api.Assertions;
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
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    /*Transactional 어노테이션이 테스트 케이스에 있으면 실행이 끝난 뒤에 바로 롤백을 함 따라서 DB엔 데이터가 저장 되지 않음*/
    /*롤백 하고 싶지 않을 경우 아래의 어노테이션 추가*/
    @Rollback(value = false)
    public void textMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        System.out.println("member.getUsername() = " + member.getUsername());

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

    }

}