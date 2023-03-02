package com.yl.user.config.time;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author simple-zhang
 * @date 2/28/2023 7:27 PM
 */
@Slf4j
@Component
public class LaunchTimeManager implements ApplicationListener<ContextRefreshedEvent> {

    private final Map<String, LaunchTime> launchTimeMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Spring container start finish");

        launchTimeMap.values().stream()
                .filter(e -> Objects.nonNull(e.getStartTime()) && Objects.nonNull(e.getEndTime()))
                .sorted((e1, e2) -> (int) (e2.getCost() - e1.getCost())).limit(20).forEach(e -> {
            log.info("start cost time， beanName:【{}】, cost:【{}】", e.getBeanName(), e.getCost());
        });
    }

    public void beanStart(String beanName, Long startTime) {
        if (Objects.nonNull(launchTimeMap.get(beanName))) {
            log.info("{} Bean repeat start ??? ", beanName);
            return;
        }
        LaunchTime launchTime = new LaunchTime();
        launchTime.setBeanName(beanName);
        launchTime.setStartTime(startTime);
        launchTimeMap.putIfAbsent(beanName, launchTime);

    }

    public void beanEnd(String beanName, Long endTime) {
        if (Objects.isNull(launchTimeMap.get(beanName))) {
            log.info("{} Bean not start ??? ", beanName);
            return;
        }
        launchTimeMap.computeIfPresent(beanName, (k, v) -> {
            v.setEndTime(endTime);
            return v;
        });

    }

    @Data
    public static class LaunchTime {

        private String beanName;
        private Long startTime;
        private Long endTime;

        public Long getCost() {
            return endTime - startTime;
        }
    }
}
