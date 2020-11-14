package bg.tuvarna.traveltickets.repository;

import bg.tuvarna.traveltickets.entity.Travel;
import bg.tuvarna.traveltickets.entity.TravelRoute;
import bg.tuvarna.traveltickets.entity.User;
import bg.tuvarna.traveltickets.repository.base.GenericCrudRepository;

import java.util.List;

public interface TravelRepository extends GenericCrudRepository<Travel, Long> {

    TravelRoute save(TravelRoute travelRoute);

    List<Travel> findAllByCompanyIdAndTravelStatusId(Long companyId, Long travelStatusId);

    List<Travel> findAllByDistributorIdAndTravelStatusId(Long distributorId, Long travelStatusId, Long requestStatusId);

    List<User> findAllDistributorsByTravelId(Long travelId, Long requestStatusId);

}