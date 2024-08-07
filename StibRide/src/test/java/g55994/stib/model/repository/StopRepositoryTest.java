/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.repository;

import g55994.stib.model.repository.StopRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import g55994.stib.model.dto.StopDto;

/**
 *
 * @author Iuliana
 */
public class StopRepositoryTest {
    
    public StopRepositoryTest() {
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
     * Test of getAll method, of class StopRepository.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        StopRepository instance = new StopRepository();
        List<StopDto> expResult = null;
        List<StopDto> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class StopRepository.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        Map.Entry<Integer, Integer> key = null;
        StopRepository instance = new StopRepository();
        StopDto expResult = null;
        StopDto result = instance.get(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
