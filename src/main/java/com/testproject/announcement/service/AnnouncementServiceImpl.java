package com.testproject.announcement.service;

import com.testproject.announcement.dao.AnnouncementDAO;
import com.testproject.announcement.entity.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementDAO announcementDAO;

    @Override
    @Transactional
    public List<Announcement> findAll() {
        return announcementDAO.findAll();
    }

    @Override
    @Transactional
    public Announcement findAnnouncementById(int announcementId) {
        return announcementDAO.findAnnouncementById(announcementId);
    }

    @Override
    @Transactional
    public void saveAnnouncement(Announcement announcement) {
        announcementDAO.saveAnnouncement(announcement);
    }

    @Override
    @Transactional
    public void deleteAnnouncement(int id) {
        announcementDAO.deleteAnnouncement(id);
    }

    @Override
    @Transactional
    public List<Announcement> showSimilarAnnouncements(Announcement announcement) {
        return announcementDAO.showSimilarAnnouncements(announcement);
    }
}
