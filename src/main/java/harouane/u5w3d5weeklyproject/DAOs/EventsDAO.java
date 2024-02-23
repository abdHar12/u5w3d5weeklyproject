package harouane.u5w3d5weeklyproject.DAOs;

import harouane.u5w3d5weeklyproject.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsDAO extends JpaRepository<Event, Integer> {

}
