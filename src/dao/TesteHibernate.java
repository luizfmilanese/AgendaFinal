package dao;

import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import vo.Contat;
import vo.Contel;

public class TesteHibernate {

    public static void main(String[] args) {
        TesteHibernate th = new TesteHibernate();

      
       //th.inserirContato();
       //th.editarContato();         
       //th.inserirChamadaNoContato(); 
       //th.editarChamadaNoContato();  
       //th.consultarContatPorId(BigInteger.valueOf(1));  
       //th.listarContatosPorNome("MARIA");  
       //th.listarTodosEmailsDeUmContato(BigInteger.valueOf(1));  
       //th.listarContatosSemNumeroTelefonico(); 
       //th.excluirChamadaNoContato();  
       //th.excluirContato();
                
        System.exit(0);
    }
    
    private void excluirContato() {
		EntityManager em = HibernateUtil.getEntityManager();
 
		try {
 
			em.getTransaction().begin();
 
			Contat contat = em.find(Contat.class, new BigInteger("2"));
			em.remove(contat);
			em.getTransaction().commit();
 
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
 
	}
    
    private void excluirChamadaNoContato() {
		EntityManager em = HibernateUtil.getEntityManager();
 
		try {
 
			em.getTransaction().begin();
 
			Contel contel = em.find(Contel.class, new BigInteger("2"));
			em.remove(contel);
			em.getTransaction().commit();
 
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
 
	}
    
    private void inserirContato() {

		EntityManager em = HibernateUtil.getEntityManager();

		Contat c = new Contat();		
		c.setDescri("");
		
		
		try {

			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();

			System.out.println("Contato inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
    }
    
    private void inserirChamadaNoContato() {

		EntityManager em = HibernateUtil.getEntityManager();

		Contat contat = em.find(Contat.class, new BigInteger("1"));
		
		Contel contel = new Contel();
		contel.setDddnum("48");
		contel.setNumero("123456789");
		contel.setEmails("maria@maria");
		contel.setContat(contat);

		try {

			em.getTransaction().begin();
			em.persist(contel);
			em.getTransaction().commit();

			System.out.println("Registrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}
    
    private void editarContato() {

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			Contat c = em.find(Contat.class, new BigInteger("1"));

			c.setDescri("Maria");
			
			c.setDescri("MARIA");
			em.merge(c);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
    	
	private void editarChamadaNoContato() {

		EntityManager em = HibernateUtil.getEntityManager();
		
		Contel contel = em.find(Contel.class, new BigInteger("2"));
		contel.setEmails("maria@xiquinha");

		try {

			em.getTransaction().begin();
			em.merge(contel);
			em.getTransaction().commit();

			System.out.println("Registrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

    private void consultarContatPorId(BigInteger id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            Contat contat = em.find(Contat.class, id);
            if (contat != null) {
                System.out.println("Contat>> " + contat.getId() + " - " + contat.getDescri());
            } else {
                System.out.println("Contat com ID " + id + " não encontrado.");
            }
        } finally {
            em.close();
        }
    }

    private void listarTodosEmailsDeUmContato(BigInteger contatId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                "SELECT c.emails FROM Contel c WHERE c.contat.id = :contatId", String.class);
            query.setParameter("contatId", contatId);
            query.getResultList().forEach(email -> System.out.println("Email: " + email));
        } finally {
            em.close();
        }
    }

    private void listarContatosPorNome(String nome) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Contat> query = em.createQuery(
                "SELECT c FROM Contat c WHERE c.descri = :nome", Contat.class);
            query.setParameter("nome", nome);
            query.getResultList().forEach(contat -> System.out.println("Contat: " + contat.getDescri()));
        } finally {
            em.close();
        }
    }

    private void listarContatosSemNumeroTelefonico() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            TypedQuery<Contat> query = em.createQuery(
                "SELECT c FROM Contat c WHERE c.id NOT IN (SELECT ce.contat.id FROM Contel ce)", Contat.class);
            query.getResultList().forEach(contat -> System.out.println("Contat sem número: " + contat.getDescri()));
        } finally {
            em.close();
        }
    }
}