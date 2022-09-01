package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     * 기존의 경우
     */
    /*
    @PersistenceContext
    private EntityManager em;
     */


    /**
     * 스프링은 EntityManger도 @Autowired로 주입 할 수 잇도록 해줌
     */
    /*
    @Autowired
    private EntityManager em;
     */

    /**
     * 따라서 @RequriedArgsConstructor 을 사용 할 수 잇음.
     * 대신 변수에 final이 반드시 있어야 함
     */
    private final EntityManager em;

    //EntityMangerFactory를 직접 주입 받고 싶을때
    //@PersistenceUnit
    //private EntityManagerFactory emf;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
