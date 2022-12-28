package com.core.security.util;
import com.core.security.AccessTokenUserDetails;
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
        if(!(principal instanceof AccessTokenUserDetails)){
            return  null;

        }

        return ((AccessTokenUserDetails) principal).userId;


    }
}
