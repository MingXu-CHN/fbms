package com.fenbi.fbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.fbms.entity.Lesson;
import com.fenbi.fbms.entity.Page;
import com.fenbi.fbms.mapper.LessonMapper;
import com.fenbi.fbms.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    public void addLesson(Lesson lesson) {
        lessonMapper.insertLesson(lesson);
    }

    public List<Lesson> queryLessons(String title, int currentPage) {
        Page page = new Page(currentPage);
        page.put("name", title);
        List<Lesson> lessons = lessonMapper.select(page);
        return lessons;
    }

    public int queryCount(String title) {
        int count = lessonMapper.selectCount(title);
        return count;
    }

    public Lesson queryLessonByLessonId(int id) {
        return lessonMapper.selectById(id);
    }

    public void modifyLesson(Lesson lesson) {
        lessonMapper.updateLesson(lesson);
    }

    public void removeLessonById(int id) {
        lessonMapper.deleteById(id);
    }

    public List<Lesson> queryByCourseId(int courseId) {
        return lessonMapper.selectByCourseId(courseId);
    }

}
