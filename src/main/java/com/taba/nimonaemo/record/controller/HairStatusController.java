package com.taba.nimonaemo.record.controller;

import com.taba.nimonaemo.global.auth.jwt.AppAuthentication;
import com.taba.nimonaemo.global.auth.role.MemberAuth;
import com.taba.nimonaemo.record.model.dto.response.ResponseHairStatusDTO;
import com.taba.nimonaemo.record.service.HairStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Tag(name = "염색/파마 기록", description = "사용자 별 염색/파마 기록 관련 api")
@RestController
@RequestMapping("/hairstatus")
@RequiredArgsConstructor
public class HairStatusController {

    private final HairStatusService hairStatusService;

    /**
     * 멤버 별 염색/파마 기록
     *
     * @param date
     * @param permFlag
     * @param dyeFlag
     */
    @PostMapping
    @MemberAuth
    public void setMemberHairStatus(AppAuthentication auth,
                                    @RequestParam(name = "DATE") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                    @RequestParam(name = "PERM_FLAG", required = false) boolean permFlag,
                                    @RequestParam(name = "DYE_FLAG", required = false) boolean dyeFlag) {
        hairStatusService.setMemberHairStatus(auth.getUserId(), date, permFlag, dyeFlag);
    }

    /**
     * 멤버 별 염색/파마 조회
     *
     * @return ResponseHairStatusDTO
     */
    @GetMapping
    @MemberAuth
    public ResponseHairStatusDTO getMemberHairStatus(AppAuthentication auth) {
        return hairStatusService.getMemberHairStatus(auth.getUserId());
    }


    /**
     * 멤버의 파마 기록 삭제
     */
    @DeleteMapping("/perm")
    @MemberAuth
    public void deleteMemberPermStatus(AppAuthentication auth) {
        hairStatusService.deleteMemberPermStatus(auth.getUserId());
    }

    /**
     * 멤버의 염색 기록 삭제
     */
    @DeleteMapping("/dye")
    @MemberAuth
    public void deleteMemberDyeStatus(AppAuthentication auth) {
        hairStatusService.deleteMemberDyeStatus(auth.getUserId());
    }
}
