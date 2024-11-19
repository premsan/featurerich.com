package com.featurerich.security.roleauthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "security_role_authority")
public class RoleAuthority {

    @Id private String id;

    @Version private Long version;

    @Column private String roleId;

    @Column private String authorityId;

    @Column private Long updatedAt;

    @Column private String updatedBy;
}
