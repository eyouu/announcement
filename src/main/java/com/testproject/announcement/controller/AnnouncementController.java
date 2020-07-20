package com.testproject.announcement.controller;

import com.testproject.announcement.entity.Announcement;
import com.testproject.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @GetMapping("/announcements")
    public List<Announcement> findAll() {
        return announcementService.findAll();
    }

    @PostMapping("/announcements")
    public Announcement saveAnnouncement(@RequestBody Announcement announcement) {
        announcement.setId(0);
        announcement.setDateAdded(LocalDate.now());
        announcementService.saveAnnouncement(announcement);
        return announcement;
    }

    @PutMapping("/announcements")
    public Announcement updateAnnouncement(@RequestBody Announcement announcement) {
        announcementService.saveAnnouncement(announcement);

        return announcement;
    }

    @DeleteMapping("/announcements/{announcementId}")
    public String deleteAnnouncement(@PathVariable int announcementId) {
        Announcement announcement = announcementService.findAnnouncementById(announcementId);
        if (announcement == null) {
            throw new RuntimeException("Announcement id not found - " + announcementId);
        }

        announcementService.deleteAnnouncement(announcementId);
        return "Deleted announcement with id - " + announcementId;
    }

    @GetMapping("/announcements/{announcementId}")
    public Announcement showAnnouncementDetails(@PathVariable int announcementId) {
        Announcement announcement = announcementService.findAnnouncementById(announcementId);
        if (announcement == null) {
            throw new RuntimeException("Announcement id not found" + announcementId);
        }
        return announcement;
    }

    @GetMapping("/announcements/similar/{announcementId}")
    public List<Announcement> showSimilarAnnouncements(@PathVariable int announcementId) {
        Announcement announcement = announcementService.findAnnouncementById(announcementId);
        if (announcement == null) {
            throw new RuntimeException("Announcement id not found" + announcementId);
        }
        return announcementService.showSimilarAnnouncements(announcement);
    }
}
