package com.spring_bandend.spring_bandend.mapper;
import com.spring_bandend.spring_bandend.entity.User;
import com.spring_bandend.spring_bandend.feature.core.user.dto.response.UserResponse;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapper {

    /** Safe outward DTO: id, username, nickName, enabled only. */
    UserResponse toResponse(User user);
}