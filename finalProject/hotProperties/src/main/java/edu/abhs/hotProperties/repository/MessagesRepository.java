package edu.abhs.hotProperties.repository;

import edu.abhs.hotProperties.entities.Messages;
import edu.abhs.hotProperties.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages,String> {
    Messages findById(long id);
    void deleteById(long id);
    void deleteByPropertyId(long propertyId);
}
