package com.chaostools.ansh.Myjims.RVfiles.Announcementfrag;

/**
 * Created by *///ansh on 23/11/17.


public class AnnRVdata {
    private String teachername,announcementdata,time,attachfiles;

    public AnnRVdata() {
    }

    public AnnRVdata(String teachername, String announcementdata, String time, String attachfiles) {
        this.teachername = teachername;
        this.announcementdata = announcementdata;
        this.time = time;
        this.attachfiles = attachfiles;
    }

    public String getTeachername() {
        return teachername;
    }
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
    public String getAnnouncementdata() {
        return announcementdata;
    }
    public void setAnnouncementdata(String announcementdata) {
        this.announcementdata = announcementdata;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAttachfiles() {
        return attachfiles;
    }

    public void setAttachfiles(String attachfiles) {
        this.attachfiles = attachfiles;
    }
}
