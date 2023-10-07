package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // unique em
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // code
        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
            // jpql
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // paging
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
