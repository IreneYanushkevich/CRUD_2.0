import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.service.LabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {

    private final Long id = 1L;
    private final String name = "Weather";

    @Mock
    private Label label;
    @Mock
    private List<Label> labels;
    @Mock
    private LabelService labelService;

    @Test
    public void createTest() {
        doReturn(label).when(labelService).create(new Label(id, name));
        assertEquals(label, labelService.create(new Label(id, name)));
    }

    @Test
    public void getByIdTest() {
        doReturn(label).when(labelService).getById(id);
        assertEquals(label, labelService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(label).when(labelService).edit(new Label(id, name));
        assertEquals(label, labelService.edit(new Label(id, name)));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(labelService).delete(id);
        assertTrue(labelService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(labels).when(labelService).getAll();
        assertEquals(labels, labelService.getAll());
    }
}