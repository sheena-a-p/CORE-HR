package com.core.form;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserLoginForm {

    @NonNull
    private String email;
    @NotNull
    private String password;

}
