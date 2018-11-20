package com.fenbi.fbms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fenbi.fbms.utils.DateUtils;

public class Lesson implements Serializable {

	private static final long serialVersionUID = -7257271902015073177L;

	private Integer id;
	private Integer courseId;
	private Integer teacherId;
	private String title;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private String courseTitle;
	private String teacherName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getEndTimeString() {
		return DateUtils.dateToString(endTime);
	}

	public String getStartTimeString() {
		return DateUtils.dateToString(startTime);
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", courseId=" + courseId + ", teacherId=" + teacherId + ", title=" + title
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", courseTitle="
				+ courseTitle + ", teacherName=" + teacherName + "]";
	}

}
