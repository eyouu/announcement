package com.testproject.announcement.dao;

import com.testproject.announcement.entity.Announcement;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AnnouncementDAOImpl implements AnnouncementDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Announcement> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Announcement", Announcement.class)
                .getResultList();
    }

    @Override
    public Announcement findAnnouncementById(int announcementId) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Announcement.class, announcementId);
    }

    @Override
    public void saveAnnouncement(Announcement announcement) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(announcement);
    }

    @Override
    public void deleteAnnouncement(int id) {
        Session session = entityManager.unwrap(Session.class);
        Announcement announcement = session.get(Announcement.class, id);
        session.delete(announcement);
    }

    @Override
    public List<Announcement> showSimilarAnnouncements(Announcement announcement) {
        Session session = entityManager.unwrap(Session.class);

        String title = announcement.getTitle().replace(" ", "|");
        String desc = announcement.getDescription().replace(" ", "|");

        return session.createSQLQuery("select * from announcement where title regexp '"+title+"' AND description regexp '"+ desc +"' limit 3;")
                .addEntity(Announcement.class)
                .list();
    }
}
