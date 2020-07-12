package com.work.chaostools.jimsadmin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ansh on 6/1/18.
 */

public class AnnouncementStructure implements Parcelable {
    String structureID;
    String  date, time,teacherName;
    String message;

    ArrayList<String> filenameArr;
    ArrayList<Uri> fileUriArr;
    ArrayList<String> filelinksArr;



    public AnnouncementStructure() {

    }

    public AnnouncementStructure(String structureID,String teacherName, String date, String time, String message,
                                 ArrayList<String> filenameArr, ArrayList<Uri> fileUriArr,
                                 ArrayList<String> filelinksArr) {
        this.teacherName = teacherName;
        this.date = date;
        this.time = time;
        this.message = message;
        this.filenameArr = filenameArr;
        this.fileUriArr = fileUriArr;
        this.filelinksArr = filelinksArr;
        this.structureID = structureID;
    }

    protected AnnouncementStructure(Parcel in) {
        teacherName = in.readString();
        date = in.readString();
        time = in.readString();
        message = in.readString();
        filenameArr = in.createStringArrayList();
        fileUriArr = in.createTypedArrayList(Uri.CREATOR);
        filelinksArr = in.createStringArrayList();
        structureID = in.readString();
    }

    public static final Creator<AnnouncementStructure> CREATOR = new Creator<AnnouncementStructure>() {
        @Override
        public AnnouncementStructure createFromParcel(Parcel in) {
            return new AnnouncementStructure(in);
        }

        @Override
        public AnnouncementStructure[] newArray(int size) {
            return new AnnouncementStructure[size];
        }
    };

    public String getTeacherName() {
        return teacherName;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getMessage() {
        return message;
    }
    public String getStructureID() {
        return structureID;
    }
    public ArrayList<String> getFilenameArr() {
        return filenameArr;
    }
    public ArrayList<Uri> getFileUriArr() {
        return fileUriArr;
    }
    public ArrayList<String> getFilelinksArr() {
        return filelinksArr;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStructureID(String structureID) {
        this.structureID = structureID;
    }
    public void setFilenameArr(ArrayList<String> filenameArr) {
        this.filenameArr = filenameArr;
    }
    public void setFileUriArr(ArrayList<Uri> fileUriArr) {
        this.fileUriArr = fileUriArr;
    }
    public void setFilelinksArr(ArrayList<String> filelinksArr) {
        this.filelinksArr = filelinksArr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teacherName);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(message);
        dest.writeStringList(filenameArr);
        dest.writeTypedList(fileUriArr);
        dest.writeStringList(filelinksArr);
        dest.writeString(structureID);
    }

    @Override
    public String toString() {
        return "AnnouncementStructure{\n" +
                "teacherName='" + teacherName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                ", filenameArr=" + filenameArr +
                ", fileUriArr=" + fileUriArr +
                ", filelinksArr=" + filelinksArr +
                ", structureID='" + structureID + '\'' +
                '}';
    }
}
