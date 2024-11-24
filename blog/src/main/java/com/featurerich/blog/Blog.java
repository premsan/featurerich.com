package com.featurerich.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "blog_blog")
public class Blog {

    @Id private String id;

    @Version private Long version;

    @Column private String title;

    @Column private String content;

    @Column private Long updatedAt;

    @Column private String updatedBy;
}
