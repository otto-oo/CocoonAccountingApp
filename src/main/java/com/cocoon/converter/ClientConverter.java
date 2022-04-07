package com.cocoon.converter;

import com.cocoon.dto.ClientDTO;
import com.cocoon.entity.Client;
import com.cocoon.repository.ClientVendorRepo;
import com.cocoon.util.MapperUtil;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationPropertiesBinding
public class ClientConverter implements Converter<String, ClientDTO> {

    private final ClientVendorRepo clientVendorRepo;
    private final MapperUtil mapperUtil;

    public ClientConverter(@Lazy ClientVendorRepo clientVendorRepo, MapperUtil mapperUtil) {
        this.clientVendorRepo = clientVendorRepo;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ClientDTO convert(String id) {

        Client client = clientVendorRepo.findById(Long.parseLong(id)).orElseThrow();
        return mapperUtil.convert(client, new ClientDTO());
    }
}
