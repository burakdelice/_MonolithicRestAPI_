package com.burakdelice.mapper;

import com.burakdelice.dto.response.UserResponseDto;
import com.burakdelice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    UserResponseDto toUserResponseDto(User user);

}
