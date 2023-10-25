package com.alibou.security.auth;

import com.alibou.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String nom;
  private String prenom;
  private String contact;
  private String email;
  private String password;
  private Role role;
}
