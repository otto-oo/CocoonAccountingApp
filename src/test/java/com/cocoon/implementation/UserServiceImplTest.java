package com.cocoon.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cocoon.dto.CompanyDTO;
import com.cocoon.entity.Company;
import com.cocoon.entity.Role;
import com.cocoon.entity.State;
import com.cocoon.entity.User;
import com.cocoon.repository.UserRepo;
import com.cocoon.service.CompanyService;
import com.cocoon.util.MapperUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private CompanyService companyService;

    @MockBean
    private MapperUtil mapperUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testFindAllUsers() {
        when(this.userRepo.findAllByCompanyId((Long) any())).thenReturn(new ArrayList<>());
        when(this.companyService.getCompanyByLoggedInUser()).thenReturn(new CompanyDTO());
        assertTrue(this.userServiceImpl.findAllUsers().isEmpty());
        verify(this.userRepo).findAllByCompanyId((Long) any());
        verify(this.companyService).getCompanyByLoggedInUser();
    }

    @Test
    void testFindAllUsers2() {
        Company company = new Company();
        company.setEmail("jane.doe@example.org");
        company.setEstablishmentDate(LocalDate.ofEpochDay(1L));
        company.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        company.setState(new State());
        company.setZip("21654");
        company.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        company.setEnabled((byte) 'A');
        company.setCreatedBy(0L);
        company.setUpdatedBy(0L);
        company.setCategories(new ArrayList<>());
        company.setAddress1("42 Main St");
        company.setAddress2("42 Main St");
        company.setIsDeleted(true);
        company.setRepresentative("Representative");
        company.setUser(new ArrayList<>());
        company.setId(123L);
        company.setPhone("4105551212");
        company.setTitle("Dr");
        company.setClient(new ArrayList<>());

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setEnabled(true);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEnabled(true);
        user.setFirstname("Jane");
        user.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedBy(0L);
        user.setUpdatedBy(0L);
        user.setIsDeleted(true);
        user.setId(123L);
        user.setPhone("4105551212");
        user.setCompany(company);
        user.setLastname("Doe");
        user.setRole(role);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userRepo.findAllByCompanyId((Long) any())).thenReturn(userList);
        when(this.mapperUtil.convert((Object) any(), (Object) any())).thenReturn(null);
        when(this.companyService.getCompanyByLoggedInUser()).thenReturn(new CompanyDTO());
        assertEquals(1, this.userServiceImpl.findAllUsers().size());
        verify(this.userRepo).findAllByCompanyId((Long) any());
        verify(this.mapperUtil).convert((Object) any(), (Object) any());
        verify(this.companyService).getCompanyByLoggedInUser();
    }
}

