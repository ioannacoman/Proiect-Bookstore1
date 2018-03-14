package ro.sci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.sci.domain.Address;
import ro.sci.domain.Client;
import ro.sci.dto.CreateClientRequest;
import ro.sci.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String listClients(Model model, HttpServletRequest request) {
        List<Client> clients = clientService.getAll();
        model.addAttribute("clients", clients);
        model.addAttribute("createClientRequest", new CreateClientRequest());

        return "listClients";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public String createClient(CreateClientRequest clientRequest, Model model) {
        Client client = getClient(clientRequest);
        clientService.createClient(client);

        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public String getClient(@PathVariable long id, Model model) {
        Client client = clientService.getById(id);
        model.addAttribute("updateClientRequest", getClientRequest(client));
        model.addAttribute("client_id", id);
        return "updateClient";
    }


    @RequestMapping(value = "/clients/update/{id}", method = RequestMethod.POST)
    public String updateClient(CreateClientRequest clientRequest, @PathVariable long id) {
        Client client = getClient(clientRequest);

        clientService.updateClient(client, id);

        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.POST)
    public String removeClient(@PathVariable long id, Model model) {
        clientService.removeClient(id);

        return "redirect:/clients";
    }

    private Client getClient(CreateClientRequest clientRequest) {

        Client client = new Client();
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setEmail(clientRequest.getEmail());
        client.setPhoneNumber(clientRequest.getPhoneNumber());

        Address address = new Address();
        address.setCity(clientRequest.getCity());
        address.setStreetName(clientRequest.getStreetName());
        address.setStreetNumber(clientRequest.getStreetNumber());
        address.setZipCode(clientRequest.getZipCode());

        client.setAddress(address);

        return client;
    }

    private CreateClientRequest getClientRequest(Client client) {
        CreateClientRequest createClientRequest = new CreateClientRequest();

        createClientRequest.setFirstName(client.getFirstName());
        createClientRequest.setLastName(client.getLastName());
        createClientRequest.setEmail(client.getEmail());
        createClientRequest.setPhoneNumber(client.getPhoneNumber());
        createClientRequest.setCity(client.getAddress().getCity());
        createClientRequest.setStreetName(client.getAddress().getStreetName());
        createClientRequest.setStreetNumber(client.getAddress().getStreetNumber());
        createClientRequest.setZipCode(client.getAddress().getZipCode());

        return createClientRequest;

    }

}
