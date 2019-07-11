package com.codegym.repository.impl;

import com.codegym.model.Blog;
import com.codegym.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryImpl implements BlogRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b",Blog.class);
        return query.getResultList();
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId()!= null){
            em.merge(blog);
        }else {
            em.persist(blog);
        }
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = em.createQuery("select p from  Blog p WHERE p.id =:id ", Blog.class);
        query.setParameter("id", id);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void delete(Blog blog) {
        Blog blogFind = findById(blog.getId());
        if (blogFind!=null){
            em.remove(blogFind);
        }
    }
    @Override
    public Blog findByName(String name){
        TypedQuery<Blog> query = em.createQuery("select p from  Blog p WHERE p.name =:name ", Blog.class);
        query.setParameter("name", name);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
