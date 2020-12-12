package bg.tuvarna.traveltickets.service;

import bg.tuvarna.traveltickets.entity.Client;
import bg.tuvarna.traveltickets.entity.ClientType;

import java.util.List;

public interface ClientService {

    Client findByUserId(Long userId);

    Client save(Client client);

    List<Client> findAll();

    ClientType findTypeById(Long id);

    ClientType findTypeByName(ClientType.Enum clientTypeName);

}
