package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Type;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity(name="speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long speaker_id;

  private String first_name;
  private String last_name;
  private String title;
  private String company;
  private String speaker_bio;

  // Large Object. Helps JPA deal with large data.
  @Lob
  //This type helps JPA deal with large binary data. We are using hibernate under the covers for
  // JPA implementations.
  @Type(type="org.hibernate.type.BinaryType")
  private byte[] speaker_photo;

  @ManyToMany(mappedBy = "speakers")
  // JsonIgnore helps in serialization, it helps to prevent looping over same entity
  // in a many to many relationship
  @JsonIgnore
  private List<Session> sessions;

  public Speaker() {
  }

  public Long getSpeaker_id() {
    return speaker_id;
  }

  public void setSpeaker_id(Long speaker_id) {
    this.speaker_id = speaker_id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getSpeaker_bio() {
    return speaker_bio;
  }

  public void setSpeaker_bio(String speaker_bio) {
    this.speaker_bio = speaker_bio;
  }

  public byte[] getSpeaker_photo() {
    return speaker_photo;
  }

  public void setSpeaker_photo(byte[] speaker_photo) {
    this.speaker_photo = speaker_photo;
  }

  public List<Session> getSessions() {
    return sessions;
  }

  public void setSessions(List<Session> sessions) {
    this.sessions = sessions;
  }

}
