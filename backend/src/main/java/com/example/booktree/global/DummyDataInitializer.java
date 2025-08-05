//package com.example.booktree.global;
//
//import com.example.booktree.domain.blog.entity.Blog;
//import com.example.booktree.domain.blog.repository.BlogRepository;
//import com.example.booktree.domain.likepost.entity.LikePost;
//import com.example.booktree.domain.likepost.repository.LikePostRepository;
//import com.example.booktree.domain.maincategory.entity.MainCategory;
//import com.example.booktree.domain.maincategory.repository.MainCategoryRepository;
//import com.example.booktree.domain.post.entity.Post;
//import com.example.booktree.domain.post.repository.PostRepository;
//import com.example.booktree.domain.user.entity.User;
//import com.example.booktree.domain.user.repository.UserRepository;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DummyDataInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final BlogRepository blogRepository;
//    private final MainCategoryRepository mainCategoryRepository;
//    private final PostRepository postRepository;
//    private final LikePostRepository likePostRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User user = userRepository.findById(2L).get();
//
//
//        MainCategory mainCategory = mainCategoryRepository.findAll().stream()
//            .findFirst()
//            .orElseThrow(() -> new RuntimeException("MainCategory not found"));
//        Blog blog = blogRepository.findById(2L)
//                .orElseThrow(() -> new RuntimeException("Blog not found"));
//// <- Lazy 필드도 정상 작동
//        for (int i = 1; i <= 100; i++) {
//            Post post = Post.builder()
//                    .title("테스트 게시글 " + i)
//                    .content("내용입니다")
//                    .user(user)
//                    .blog(blog)
//                    .mainCategory(mainCategory)
//                    .build();
//
//            postRepository.save(post);
//        }
//
//        // 좋아요도 같이 누르기
//        List<Post> posts = postRepository.findAll();
//        for (Post post : posts) {
//            LikePost like = LikePost.builder()
//                    .user(user)
//                    .post(post)
//                    .build();
//            likePostRepository.save(like);
//        }
//    }
//}
