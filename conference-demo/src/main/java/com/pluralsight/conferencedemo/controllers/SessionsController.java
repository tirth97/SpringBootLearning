package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {

  @Autowired
  private SessionRepository sessionRepository;

  @GetMapping
  public List<Session> list() {
    return sessionRepository.findAll();
  }

  @GetMapping
  @RequestMapping("{id}")
  public Session get(@PathVariable Long id) {
    return sessionRepository.getOne(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Session create(@RequestBody final Session session) {
    return sessionRepository.saveAndFlush(session);
  }

  @RequestMapping(value="{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable Long id) {
    // Kinda Todo: Need to check for children records before deleting.
    sessionRepository.deleteById(id);
  }

  @RequestMapping(value="{id}", method = RequestMethod.PUT)
  public Session update(@PathVariable Long id, @RequestBody Session session) {
    //As this is a PUT, we expect all attributes to be passed in. A PATCH
    // TODO: add validation that all elements are passed in, otherwise return a 400 bad payload
    Session existingSession = sessionRepository.getOne(id);
    BeanUtils.copyProperties(session, existingSession, "session_id");
    return sessionRepository.saveAndFlush(existingSession);
  }

}
