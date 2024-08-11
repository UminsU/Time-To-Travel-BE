package logX.TTT.post;

import jakarta.persistence.*;
import logX.TTT.comment.Comment;
import logX.TTT.likes.Likes;
import logX.TTT.location.Location;
import logX.TTT.member.Member;
import logX.TTT.views.Views;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Views> views = new ArrayList<>();

    @Column(name = "image_url")
    private String imageUrl;

    public void addView() {
        if (this.views == null) {
            this.views = new ArrayList<>();
        }
        Views view = new Views();
        this.views.add(view);
    }

    public int getViewCount() {
        return this.views != null ? this.views.size() : 0;
    }
}
