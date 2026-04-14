package com.personal.ocr_project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.personal.ocr_project.dto.ScanHistoryDto;
import com.personal.ocr_project.dto.UserDto;
import com.personal.ocr_project.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        List<ScanHistoryDto> scanHistoryDto = null;
        if (user.getScanHistory() != null) {
            scanHistoryDto = user.getScanHistory().stream().map(ScanHistoryMapper::toDto)
                    .collect(Collectors.toList());
        }

        return new UserDto(user.getId(), user.getName(), user.getUsername(), scanHistoryDto);
    }
}
