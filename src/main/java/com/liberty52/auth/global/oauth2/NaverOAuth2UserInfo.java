package com.liberty52.auth.global.oauth2;


import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

  private final Map<String, Object> response;
  public NaverOAuth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
    response = (Map<String, Object>) attributes.get("response");
  }

  @Override
  public String getId() {
    if (response == null) {
      return null;
    }
    return (String) response.get("id");
  }

  @Override
  public String getPhoneNumber() {
    if (response == null) {
      return null;
    }
    return (String) response.get("mobile");
  }

  @Override
  public String getImageUrl() {
    if (response == null) {
      return null;
    }

    return (String) response.get("profile_image");
  }

  @Override
  public String getEmail() {
    if (response == null) {
      return null;
    }

    return (String) response.get("email");
  }

  @Override
  public String getName() {
    if(response == null)
        return null;
    return (String) response.get("name");
  }
}

