package vitalii.shapovalov.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import vitalii.shapovalov.library.entity.Book;

public class BookDao {
	
	private EntityManager em = Persistence.createEntityManagerFactory("primary").createEntityManager();

	public Book getById(long id){
		return em.find(Book.class, id);
	}
	
	public List<Book> findByName(String name){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = builder.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		cq.where(builder.like(root.get("name"),"%"+name+"%"));
		return em.createQuery(cq).getResultList();
	}
	
	
	public void addBook(Book book){
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
	}
	
	public List<Book> getAll(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = builder.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		cq.select(root);
		return em.createQuery(cq).getResultList();
	}
	
	public void update(Book book){
		em.getTransaction().begin();
		em.merge(book);
		em.getTransaction().commit();
	}
	
    public void delete(final Book book) {
    	em.getTransaction().begin();
        em.remove(em.merge(book));
        em.getTransaction().commit();
    }
	
	
	
}
