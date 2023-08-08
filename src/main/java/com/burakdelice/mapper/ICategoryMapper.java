package com.burakdelice.mapper;

import com.burakdelice.dto.response.UserResponseDto;
import com.burakdelice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {

    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);
    UserResponseDto toUserResponseDto(User user);
    User toUser(UserResponseDto dto);

    List<UserResponseDto> toUserResponseDtos(List<User> userList);
}
