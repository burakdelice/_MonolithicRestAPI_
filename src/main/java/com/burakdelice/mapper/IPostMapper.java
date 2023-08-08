package com.burakdelice.mapper;

import com.burakdelice.dto.response.UserResponseDto;
import com.burakdelice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPostMapper {

    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);
    UserResponseDto toPostResponseDto(User user);

}
