package com.chaostools.ansh.Myjims.RVfiles.Courses;

/**
 * Created by ansh on 14/11/17.
 */

public class RVdata_crs {
   private String Coursename, degree, duration, eligibility, exam, coursefee;

    public RVdata_crs() {
    }

    public RVdata_crs(String coursename, String degree, String duration, String eligibility, String exam, String coursefee) {
        Coursename = coursename;
        this.degree = degree;
        this.duration = duration;
        this.eligibility = eligibility;
        this.exam = exam;
        this.coursefee = coursefee;

    }

    public String getCoursename() {
        return Coursename;
    }

    public String getDegree() {
        return degree;
    }

    public String getDuration() {
        return duration;
    }

    public String getEligibility() {
        return eligibility;
    }

    public String getExam() {
        return exam;
    }

    public String getCoursefee() {
        return coursefee;
    }

    public void setCoursename(String coursename) {
        Coursename = coursename;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public void setCoursefee(String coursefee) {
        this.coursefee = coursefee;
    }
}
