import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.Writer;
import com.irinayanushkevich.crud_2.repository.WriterRepository;
import com.irinayanushkevich.crud_2.service.WriterService;
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
public class WriterServiceTest {
    private final Long id = 1L;
    private final List<Post> posts = new ArrayList<>();
    private final WriterRepository writerRepository = Mockito.mock(WriterRepository.class);
    private final WriterService writerService = new WriterService(writerRepository);

    private Writer getWriter() {
        return new Writer(id, "Aleksandr", "Pushkin", posts);
    }

    private List<Writer> getWriters() {
        List<Writer> writers = new ArrayList<>();
        writers.add(getWriter());
        return writers;
    }

    @Test
    public void createTest() {
        doReturn(getWriter()).when(writerRepository).create(any(Writer.class));
        assertEquals(getWriter(), writerService.create(getWriter()));
    }

    @Test
    public void getByIdTest() {
        doReturn(getWriter()).when(writerRepository).getById(anyLong());
        assertEquals(getWriter(), writerService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(getWriter()).when(writerRepository).edit(any(Writer.class));
        assertEquals(getWriter(), writerService.edit(getWriter()));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(writerRepository).delete(anyLong());
        assertTrue(writerService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(getWriters()).when(writerRepository).getAll();
        assertEquals(getWriters(), writerService.getAll());
    }
}
