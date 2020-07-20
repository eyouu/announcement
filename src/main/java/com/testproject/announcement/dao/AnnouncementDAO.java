package com.testproject.announcement.dao;

import com.testproject.announcement.entity.Announcement;

import java.util.List;

public interface AnnouncementDAO {
    List<Announcement> findAll();

    Announcement findAnnouncementById(int announcementId);

    void saveAnnouncement(Announcement announcement);

    void deleteAnnouncement(int id);

    List<Announcement> showSimilarAnnouncements(Announcement announcement);
}
