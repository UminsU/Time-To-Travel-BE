package logX.TTT.interest;

import logX.TTT.member.MemberService;
import logX.TTT.post.Post;
import logX.TTT.post.PostRepository;
import logX.TTT.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private MemberService memberService;

    public List<Post> getRecommendedPosts(String username) {
        Long memberId = memberService.getMemberIdByUsername(username);
        List<String> recentQueries = searchService.getRecentSearchQueries(memberId);
        List<Post> recommendedPosts = new ArrayList<>();
        for (String query : recentQueries) {
            recommendedPosts.addAll(postRepository.findByTitleContainingOrContentContaining(query));
        }
        return recommendedPosts;
    }
}