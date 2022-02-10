package com.cocoon.implementation;

import com.cocoon.dto.ClientVendorDTO;
import com.cocoon.entity.ClientVendor;
import com.cocoon.exception.CocoonException;
import com.cocoon.repository.ClientVendorRepo;
import com.cocoon.service.ClientVendorService;
import com.cocoon.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private ClientVendorRepo clientVendorRepo;
    private MapperUtil mapperUtil;

    public ClientVendorServiceImpl(ClientVendorRepo clientVendorRepo, MapperUtil mapperUtil) {
        this.clientVendorRepo = clientVendorRepo;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ClientVendorDTO> getAllClientsVendors() {
        List<ClientVendor> list = clientVendorRepo.findAll();
        return list.stream().map(client -> mapperUtil.convert(client, new ClientVendorDTO())).collect(Collectors.toList());
    }

    @Override
    public void save(ClientVendorDTO clientVendorDTO) throws CocoonException {
        //if same client name already exists in client_vendor table an exception is thrown
        if (clientVendorRepo.existsByCompanyName(clientVendorDTO.getCompanyName()))
            throw new CocoonException("This same name already saved to database.");

        //when a client is saved it is assumed that it's status is enabled
        clientVendorDTO.setEnabled(true);

        //if the length of the address exceeds 254 then it should be shortened
        if (clientVendorDTO.getAddress().length() > 254)
            throw new CocoonException("Address length should be lesser then 255");

        ClientVendor savedClient = clientVendorRepo.save(mapperUtil.convert(clientVendorDTO, new ClientVendor()));
    }

}
