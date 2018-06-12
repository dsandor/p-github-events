package com.dsandor.github;

import com.dsandor.github.models.Actor;
import com.dsandor.github.models.Event;
import com.dsandor.github.repositories.ActorRepository;
import com.dsandor.github.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApi {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private EventRepository eventRepository;

    @DeleteMapping("/events")
    public void deleteEvents() {
        eventRepository.deleteAll();
    }

    @GetMapping("/actors")
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @PostMapping("/events")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        if (event.getId() != null) {
            Optional<Event> existingEvent = eventRepository.findById(event.getId());
            if (existingEvent.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        event.setActor(actorRepository.save(event.getActor()));
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/actors")
    public Actor saveActor(@RequestBody Actor actor) {
        Actor result =  actorRepository.save(actor);

        return result;
    }

    @GetMapping("/events")
    public Iterable<Event> getEvents() {
        return eventRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/events/actors/{actorID}")
    public ResponseEntity<?> getEventsByActor(@PathVariable Long actorID) {
        List<Event> events = eventRepository.findAllByActorId(actorID);

        if (events.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(events);
    }
}
