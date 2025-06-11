package edu.abhs.hotProperties.repository;

import edu.abhs.hotProperties.entities.Favorite;
import edu.abhs.hotProperties.entities.Property;
import edu.abhs.hotProperties.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findByBuyer(User user);

    Favorite findByBuyerAndProperty(User u, Property p);

    boolean existsByBuyerAndProperty(User u, Property property);

    Long countByBuyer(User user);
    Favorite findByProperty(Property property);
}
