package core.dao;

import core.domain.entity.Note;
import core.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NotesDaoImpl implements NotesDao {
    public void add(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(note);
        tx.commit();
        session.close();
    }

    public void delete(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(note);
        tx.commit();
        session.close();
    }

    public void update(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(note);
        tx.commit();
        session.close();
    }

    public List<Note> getAll() {
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Note");
        return (List<Note>) query.list();
    }

    @Override
    public List<Note> getByTitle(String title) {
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Note n where n.title = :title");
        query.setParameter("title", title);
        return (List<Note>) query.list();

    }

    @Override
    public List<Note> getByContent(String content) {
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Note n where n.content like :content");
        query.setParameter("content", "%" + content + "%");
        return (List<Note>) query.list();
    }

    @Override
    public List<Note> getByCreateDate(Date date) {
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Note n where n.date = :date");
        query.setParameter("date", date);
        return (List<Note>) query.list();
    }

    @Override
    public Optional<Note> getById(Long id) {
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Note n where n.id = :id");
        query.setParameter("id", id);
        return (Optional<Note>) query.uniqueResultOptional();
    }
}
