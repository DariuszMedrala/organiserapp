package pl.dariuszmedrala.organiser.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dariuszmedrala.organiser.models.entities.AlertEntity;

import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<AlertEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM alert")
    List<AlertEntity> findAllAlerts();
    @Query(nativeQuery = true, value = "SELECT * FROM alert JOIN user ON alert.user_id = user.id WHERE login =?1")
    List<AlertEntity> findAllByUserLogin(String login);
}
