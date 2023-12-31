package com.b106_402jeoung.PIYOU.controller;

import com.b106_402jeoung.PIYOU.dto.NotificationRequest;
import com.b106_402jeoung.PIYOU.dto.PushRequest;
import com.b106_402jeoung.PIYOU.service.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noti")
public class NotificationController {
    private final PushService pushService;

    @GetMapping("{childId}")
    public ResponseEntity<?> getNoti(@PathVariable UUID childId) {
        return ResponseEntity.ok(pushService.getPush(childId));
    }

    @PostMapping("/send")
    public String sendPush(@RequestBody PushRequest pushRequest) {
        pushService.sendPush(pushRequest);

        return "등록완료";
    }

    @PostMapping("/{childId}")
    public ResponseEntity<?> registerPush(@RequestBody NotificationRequest notificationRequest,
                                          @PathVariable UUID childId) {
        return ResponseEntity.ok(pushService.registerPush(notificationRequest, childId));
    }

    @DeleteMapping("/{notiId}")
    public String deletePush(@PathVariable Long notiId) {
        pushService.deletePush(notiId);
        return "삭제되었습니다.";
    }
}
