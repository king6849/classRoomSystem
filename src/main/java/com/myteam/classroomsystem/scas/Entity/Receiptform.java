package com.myteam.classroomsystem.scas.Entity;


public class Receiptform {

  private long id;
  private String name;
  private java.sql.Timestamp applicationTime;
  private java.sql.Timestamp endtime;
  private String classroom;
  private String note;
  private long cid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public java.sql.Timestamp getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(java.sql.Timestamp applicationTime) {
    this.applicationTime = applicationTime;
  }


  public java.sql.Timestamp getEndtime() {
    return endtime;
  }

  public void setEndtime(java.sql.Timestamp endtime) {
    this.endtime = endtime;
  }


  public String getClassroom() {
    return classroom;
  }

  public void setClassroom(String classroom) {
    this.classroom = classroom;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }

}
