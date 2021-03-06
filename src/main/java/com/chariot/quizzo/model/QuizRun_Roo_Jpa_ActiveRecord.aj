// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.quizzo.model;

import com.chariot.quizzo.model.QuizRun;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect QuizRun_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager QuizRun.entityManager;
    
    public static final EntityManager QuizRun.entityManager() {
        EntityManager em = new QuizRun().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long QuizRun.countQuizRuns() {
        return entityManager().createQuery("SELECT COUNT(o) FROM QuizRun o", Long.class).getSingleResult();
    }
    
    public static List<QuizRun> QuizRun.findAllQuizRuns() {
        return entityManager().createQuery("SELECT o FROM QuizRun o", QuizRun.class).getResultList();
    }
    
    public static QuizRun QuizRun.findQuizRun(Long id) {
        if (id == null) return null;
        return entityManager().find(QuizRun.class, id);
    }
    
    public static List<QuizRun> QuizRun.findQuizRunEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM QuizRun o", QuizRun.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void QuizRun.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void QuizRun.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            QuizRun attached = QuizRun.findQuizRun(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void QuizRun.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void QuizRun.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public QuizRun QuizRun.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        QuizRun merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
