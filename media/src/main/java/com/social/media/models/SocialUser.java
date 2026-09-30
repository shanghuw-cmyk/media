package com.social.media.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.*;

import static java.util.Objects.hash;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE})
    private SocialProfile socialProfile;


    @OneToMany(mappedBy = "socialUser", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )

    private Set<SocialGroup> groups = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialProfile socialProfile){
        this.socialProfile = socialProfile;
        socialProfile.setUser(this);
    }

}
