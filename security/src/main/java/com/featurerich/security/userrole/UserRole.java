package com.featurerich.security.userrole;

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
@Table(name = "user_roles")
public class UserRole {

    @Id private String id;

    @Version private Long version;

    @Column private String userId;

    @Column private String roleId;

    @Column private Long updatedAt;

    @Column private String updatedBy;
}
