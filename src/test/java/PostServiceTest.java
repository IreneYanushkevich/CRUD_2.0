import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.repository.PostRepository;
import com.irinayanushkevich.crud_2.service.PostService;
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
public class PostServiceTest {
    private final Long id = 1L;
    private final List<Label> labels = new ArrayList<>();
    private final PostRepository postRepository = Mockito.mock(PostRepository.class);
    private final PostService postService = new PostService(postRepository);

    private Post getPost() {
        return new Post(id, "Post content", labels);
    }

    private List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(getPost());
        return posts;
    }

    @Test
    public void createTest() {
        doReturn(getPost()).when(postRepository).create(any(Post.class));
        assertEquals(getPost(), postService.create(getPost()));
    }

    @Test
    public void getByIdTest() {
        doReturn(getPost()).when(postRepository).getById(anyLong());
        assertEquals(getPost(), postService.getById(id));
    }

    @Test
    public void editTest() {
        doReturn(getPost()).when(postRepository).edit(any(Post.class));
        assertEquals(getPost(), postService.edit(getPost()));
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(postRepository).delete(anyLong());
        assertTrue(postService.delete(id));
    }

    @Test
    public void getAllTest() {
        doReturn(getPosts()).when(postRepository).getAll();
        assertEquals(getPosts(), postService.getAll());
    }
}