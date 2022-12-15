package fr.solutec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Schedule;
import fr.solutec.entities.User;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

		public List<Schedule> findByUserSchedule(Optional<User> u);
}
