package com.military.recruit.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar

@Configuration
@EnableScheduling
class SchedulerConfig : SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        val avail = Runtime.getRuntime().availableProcessors()
        val threadPool = ThreadPoolTaskScheduler()
        threadPool.poolSize = avail
        threadPool.setThreadNamePrefix("Military-Recruit-Scheduler-")
        threadPool.initialize()

        taskRegistrar.setTaskScheduler(threadPool)
    }

}