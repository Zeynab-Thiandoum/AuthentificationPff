package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alibou.security.user.Permission.admin_CREATE;
import static com.alibou.security.user.Permission.admin_DELETE;
import static com.alibou.security.user.Permission.admin_READ;
import static com.alibou.security.user.Permission.admin_UPDATE;
import static com.alibou.security.user.Permission.client_CREATE;
import static com.alibou.security.user.Permission.client_DELETE;
import static com.alibou.security.user.Permission.client_READ;
import static com.alibou.security.user.Permission.client_UPDATE;
import static com.alibou.security.user.Permission.paysan_CREATE;
import static com.alibou.security.user.Permission.paysan_DELETE;
import static com.alibou.security.user.Permission.paysan_READ;
import static com.alibou.security.user.Permission.paysan_UPDATE;
import static com.alibou.security.user.Permission.employe_CREATE;
import static com.alibou.security.user.Permission.employe_DELETE;
import static com.alibou.security.user.Permission.employe_READ;
import static com.alibou.security.user.Permission.employe_UPDATE;
@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  admin(
          Set.of(
                  admin_READ,
                  admin_UPDATE,
                  admin_DELETE,
                  admin_CREATE,
                  client_CREATE,
                  client_DELETE,
                  client_READ,
                  client_UPDATE
          )
  ),
  client(
          Set.of(
                  client_CREATE,
                  client_UPDATE,
                  client_READ,
                  client_DELETE
          )
  ),



  paysan(
          Set.of(
          paysan_CREATE,
          paysan_DELETE,
          paysan_READ,
          paysan_UPDATE
          )
  ),

  employe(
          Set.of(
          employe_CREATE,
          employe_DELETE,
          employe_READ,
          employe_UPDATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
