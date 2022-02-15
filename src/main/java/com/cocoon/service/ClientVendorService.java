package com.cocoon.service;

import com.cocoon.dto.ClientVendorDTO;
import com.cocoon.exception.CocoonException;

import java.util.List;

public interface ClientVendorService {

    List<ClientVendorDTO> getAllClientsVendors();

    void save(ClientVendorDTO clientVendorDTO) throws CocoonException;

    List<ClientVendorDTO> getAllClientsVendorsActivesFirst();

 //   ClientVendorDTO findByEmail(String email) throws CocoonException;

    ClientVendorDTO findById(Long id) throws CocoonException;

    ClientVendorDTO update (ClientVendorDTO clientVendorDTO) throws CocoonException;

    void deleteClientVendor(Long id) throws CocoonException;

}
