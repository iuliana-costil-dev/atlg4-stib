/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.repository;

import g55994.stib.model.repository.StationRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import g55994.stib.model.dto.StationDto;

/**
 *
 * @author Iuliana
 */
public class StationRepositoryTest {
    
    public StationRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class StationRepository.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        StationRepository instance = new StationRepository();
        List<StationDto> expResult = null;
        List<StationDto> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class StationRepository.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        Integer key = null;
        StationRepository instance = new StationRepository();
        StationDto expResult = null;
        StationDto result = instance.get(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
