package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.AllowedEmail;

public interface AllowedEmailServiceInterface {
    AllowedEmail findByName(String name);

    AllowedEmail UpdateEmail(AllowedEmail allowedEmail);
}
