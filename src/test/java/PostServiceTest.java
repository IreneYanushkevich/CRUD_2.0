import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.service.PostService;
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
public class PostServiceTest {

    private final Long id = 1L;
    private final String content = "Post content";
    private final List<Label> labels = new ArrayList<>();

    @Mock
    private List<Post> posts;
    @Mock
    private Post post;
    @Mock
    private PostService postService;

    @Test
    public void createTest() {
        doReturn(post).when(postService).create(new Post(id, content, labels));
        assertEquals(post, postService.create(new Post(id, content, labels)));
    }

    @Test
    public void getByIdTest() {
        doReturn(post).when(postService).getById(id);
        assertEquals(post, postService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(post).when(postService).edit(new Post(id, content, labels));
        assertEquals(post, postService.edit(new Post(id, content, labels)));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(postService).delete(id);
        assertTrue(postService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(posts).when(postService).getAll();
        assertEquals(posts, postService.getAll());
    }
}