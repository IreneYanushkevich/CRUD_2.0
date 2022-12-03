import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.repository.LabelRepository;
import com.irinayanushkevich.crud_2.service.LabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {
    private final Long id = 1L;

    private final LabelRepository labelRepository = Mockito.mock(LabelRepository.class);
    private final LabelService labelService = new LabelService(labelRepository);

    private Label getLabel() {
        return new Label(id, "Weather");
    }

    private List<Label> getLabels() {
        List<Label> labels = new ArrayList<>();
        labels.add(getLabel());
        return labels;
    }

    @Test
    public void createTest() {
        doReturn(getLabel()).when(labelRepository).create(any(Label.class));
        assertEquals(getLabel(), labelService.create(getLabel()));
    }

    @Test
    public void getByIdTest() {
        doReturn(getLabel()).when(labelRepository).getById(anyLong());
        assertEquals(getLabel(), labelService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(getLabel()).when(labelRepository).edit(any(Label.class));
        assertEquals(getLabel(), labelService.edit(getLabel()));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(labelRepository).delete(anyLong());
        assertTrue(labelService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(getLabels()).when(labelRepository).getAll();
        assertEquals(getLabels(), labelService.getAll());
    }
}
