package ro.sci.service;

import org.springframework.stereotype.Service;
import ro.sci.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    public List<Client> clients = new ArrayList<>();

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void createClient(Client client) {
        client.setId(System.currentTimeMillis());
        clients.add(client);
    }

    @Override
    public void removeClient(long id) {
        clients = clients.stream().filter(c -> c.getId() != id).collect(Collectors.toList());
    }

    @Override
    public void updateClient(Client client, long id) {
        removeClient(id);
        client.setId(id);
        clients.add(client);
    }

    @Override
    public Client getById(long id) {
        //TODO check that the id exists
        return clients.stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
    }

}