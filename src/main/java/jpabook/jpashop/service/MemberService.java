package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    //주입방식
    /**
     * 1. 필드주입
     * 2. setter주입
     * 3. 생성자 주입
     * 4. AllArgsConstructor
     * 5. RequiredArgsConstructor
     */

    /**
     * 필드 주입 방식
     * - 테스트 하는데 어려움
     * - final을 붙여주는 것을 권장함
     */
    //@Autowired
    private final MemberRepository memberRepository;

    /**
     * setter방식
     */
    /*
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 생성자 Injection 방식
     * 최신 스프링의 경우 생성자가 단 하나인 경우 @Autowired를 생략 가능
     */
    /*
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */


    //------------------------------

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    /**
     *  회원 전체 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 단건조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    // 중복 회원 검정 메서드
    // 문제가 생길 경우 예외(EXCEPTION)를 터트림
    // 이렇게 해주더라도 문제가 생길 수 잇음 따라서 DB에 유니크 제약 조건을 걸어주는 것이 안전
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


}
