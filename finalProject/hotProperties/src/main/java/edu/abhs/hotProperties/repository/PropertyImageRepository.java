package edu.abhs.hotProperties.repository;

import edu.abhs.hotProperties.entities.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Integer> {
    PropertyImage findById(long imageId);
}
