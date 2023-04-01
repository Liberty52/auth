package com.liberty52.auth.global.oauth2;

import com.liberty52.auth.service.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

  private String email;
  private String id;
  private Role role;

  public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
      Map<String, Object> attributes, String nameAttributeKey,
      String email, Role role, String id) {
    super(authorities, attributes, nameAttributeKey);
    this.email = email;
    this.id = id;
    this.role = role;
  }
}

