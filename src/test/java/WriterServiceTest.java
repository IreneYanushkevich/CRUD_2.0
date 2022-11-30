import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.Writer;
import com.irinayanushkevich.crud_2.service.WriterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class WriterServiceTest {

    private final Long id = 1L;
    private final String firstName = "Aleksandr";
    private final String lastName = "Pushkin";
    private final List<Post> posts = new ArrayList<>();

    @Mock
    private Writer writer;
    @Mock
    private List<Writer> writers;
    @Mock
    private WriterService writerService;

    @Test
    public void createTest() {
        doReturn(writer).when(writerService).create(new Writer(id, firstName, lastName, posts));
        assertEquals(writer, writerService.create(new Writer(id, firstName, lastName, posts)));
    }

    @Test
    public void getByIdTest() {
        doReturn(writer).when(writerService).getById(id);
        assertEquals(writer, writerService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(writer).when(writerService).edit(new Writer(id, firstName, lastName, posts));
        assertEquals(writer, writerService.edit(new Writer(id, firstName, lastName, posts)));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(writerService).delete(id);
        assertTrue(writerService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(writers).when(writerService).getAll();
        assertEquals(writers, writerService.getAll());
    }
}
