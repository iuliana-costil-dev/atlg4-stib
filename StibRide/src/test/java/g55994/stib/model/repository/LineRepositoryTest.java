/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.repository;

import g55994.stib.model.repository.LineRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import g55994.stib.model.dto.LineDto;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dao.LineDao;

/**
 *
 * @author Iuliana
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LineRepositoryTest {

    @Mock
    private LineDao mock;

    private final LineDto one;
    private final LineDto seven;

    private static final int KEY = 1;

    private final List<LineDto> all;

    public LineRepositoryTest() {
        System.out.println("StudentRepositoryTest Constructor");
        //Test data
        one = new LineDto(KEY);
        seven = new LineDto(7);
        all = new ArrayList<>();
        all.add(one);
        all.add(new LineDto(2));
        all.add(new LineDto(5));
        all.add(new LineDto(6));
    }

    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        Mockito.lenient().when(mock.select(one.getKey())).thenReturn(one);
        Mockito.lenient().when(mock.select(seven.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(all);
        Mockito.lenient().when(mock.select(null)).thenThrow(RepositoryException.class);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        //Arrange
        LineDto expected = one;
        LineRepository repository = new LineRepository(mock);
        //Action
        LineDto result = repository.get(KEY);
        //Assert        
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(KEY);
    }

    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testGetNotExist");
        //Arrange
        LineRepository repository = new LineRepository(mock);
        //Action
        LineDto result = repository.get(seven.getKey());
        //Assert        
        assertNull(result);
        Mockito.verify(mock, times(1)).select(seven.getKey());
    }

    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testGetIncorrectParameter");
        //Arrange
        LineRepository repository = new LineRepository(mock);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            repository.get(incorrect);
        });
        Mockito.verify(mock, times(1)).select(null);
    }
        @Test
    public void testGetAllExist() throws Exception {
        System.out.println("testGetAllExist");
        //Arrange
        LineDto expected = one;
        LineRepository repository = new LineRepository(mock);
        //Action
        var result = repository.getAll();
        //Assert        
        assertEquals(all, result);
        Mockito.verify(mock, times(1)).selectAll();
    }
}
