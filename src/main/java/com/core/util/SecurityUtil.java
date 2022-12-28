package com.core.util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil(){
    }

    public static Integer getCurrentUserId(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth==null|| !auth.isAuthenticated()){
            return  null;
        }
        Object principal =auth.getPrincipal();
        if(!(principal instanceof UserAccess)){
            return  null;
        }
        return ((UserAccess) principal).userId;
    }
}
