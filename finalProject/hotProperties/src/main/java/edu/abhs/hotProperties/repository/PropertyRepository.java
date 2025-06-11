package edu.abhs.hotProperties.repository;

import edu.abhs.hotProperties.entities.User;
import edu.abhs.hotProperties.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findPropertyByUser(User user);

    Property findPropertyByTitle(String title);

    Property findPropertyById(Long id);

    List<Property> findByOrderByPriceAsc();

    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price between :minPrice and :maxPrice order by price asc",
            nativeQuery = true)
    List<Property> findPropertyByWithAllFiltersAsc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt,
                                                   @Param("minPrice") String minPrice, @Param("maxPrice") String maxPrice);

    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price between :minPrice and :maxPrice order by price desc",
            nativeQuery = true)
    List<Property> findPropertyByWithAllFiltersDesc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt,
                                                    @Param("minPrice") String minPrice, @Param("maxPrice") String maxPrice);


    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt order by price asc",
            nativeQuery = true)
    List<Property> findPropertyByFiltersNoMinMaxAsc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt);

    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt order by price desc",
            nativeQuery = true)
    List<Property> findPropertyByFiltersNoMinMaxDesc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt);


    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price >= :minPrice order by price asc",
            nativeQuery = true)
    List<Property> findPropertyGreaterThanMinPriceAsc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt, @Param("minPrice") String minPrice);

    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price >= :minPrice order by price desc",
            nativeQuery = true)
    List<Property> findPropertyGreaterThanMinPriceDesc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt, @Param("minPrice") String minPrice);


    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price <= :maxPrice order by price asc",
            nativeQuery = true)
    List<Property> findPropertyLessThanMaxPriceAsc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt, @Param("maxPrice") String maxPrice);

    @Query(value = "select * from property p where p.location like %:zipcode% and p.size >= :minSqFt and p.price <= :maxPrice order by price desc",
            nativeQuery = true)
    List<Property> findPropertyLessThanMaxPriceDesc(@Param("zipcode") String zipcode, @Param("minSqFt") String minSqFt, @Param("maxPrice") String maxPrice);
    Property findPropertyById(long id);
    void deletePropertyById(long id);

    @Query("select p.user.id from Property p where p.id = :propertyId")
    Long findUserIdByPropertyId(@Param("propertyId") Long propertyId);

}
