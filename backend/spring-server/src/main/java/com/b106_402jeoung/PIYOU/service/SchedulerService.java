package com.b106_402jeoung.PIYOU.service;

import com.b106_402jeoung.PIYOU.dto.PushRequest;
import com.b106_402jeoung.PIYOU.entity.Child;
import com.b106_402jeoung.PIYOU.entity.ChildNoti;
import com.b106_402jeoung.PIYOU.repository.ChildNotiRepository;
import com.b106_402jeoung.PIYOU.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final ChildNotiRepository childNotiRepository;
    private final PushService pushService;
    private final ChildRepository childRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Transactional
    public void updateChildExp() {
        List<Child> childList = childRepository.findByName("batch_test");
        int count = 0;

        for (Child child : childList) {
            child.minusExperience(4);
            if (++count % batchSize == 0) {
                entityManager.flush();
            }
        }

        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public void updateChildIsMeal() {
        List<Child> childList = childRepository.findAllByIsMealTrue();
        int count = 0;

        for (Child child : childList) {
            child.isHungry();
            if (++count % batchSize == 0) {
                entityManager.flush();
            }
        }

        entityManager.flush();
        entityManager.clear();
    }

    public void pushTracking() {
        List<ChildNoti> oldNoti = childNotiRepository.findOldNoti();

        for (ChildNoti childNoti : oldNoti) {
            pushService.sendPush(PushRequest.builder()
                                         .title("밥 먹기 10분 전이에요")
                                         .message("방송 시간을 지켜주세요.\n" + "시청자들과의 약속을 지켜주세요")
                                         .tokenList(childNoti.getChild()
                                                            .getToken())
                                         .build());
        }
    }
}
