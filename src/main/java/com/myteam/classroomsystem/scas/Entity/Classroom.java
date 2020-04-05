package com.myteam.classroomsystem.scas.Entity;


public class Classroom {

  private long id;
  private String code;
  private long status;
  private long type;
  private long maxcapacity;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getMaxcapacity() {
    return maxcapacity;
  }

  public void setMaxcapacity(long maxcapacity) {
    this.maxcapacity = maxcapacity;
  }

}
