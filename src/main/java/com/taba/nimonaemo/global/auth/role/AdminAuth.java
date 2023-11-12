package com.taba.nimonaemo.global.auth.role;

import com.taba.nimonaemo.global.auth.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SecurityRequirement(name = JwtProvider.AUTHORIZATION)
@Secured(MemberAuthNames.ROLE_ADMIN)
public @interface AdminAuth {
}
