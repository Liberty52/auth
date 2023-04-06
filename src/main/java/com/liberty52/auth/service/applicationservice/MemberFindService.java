package com.liberty52.auth.service.applicationservice;

import java.util.List;

public interface MemberFindService {
   List<String> findEmailByPhoneNumberAndName(String phoneNumber, String name);
}
