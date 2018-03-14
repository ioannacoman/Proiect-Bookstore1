package ro.sci.service;

import ro.sci.domain.Client;

import java.util.List;

public interface ClientService {
   List<Client> getAll();

    void createClient(Client clients);

    void removeClient(long id);

    void updateClient(Client client, long id);

    Client getById(long id);
}
