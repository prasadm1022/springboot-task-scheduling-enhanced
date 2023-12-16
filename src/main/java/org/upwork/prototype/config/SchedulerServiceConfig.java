package org.upwork.prototype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Scheduler Configurations
 *
 * @author prasadm
 * @since 20 Dec 2022
 */

@Configuration
public class SchedulerServiceConfig
{
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler()
    {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize( 5 );
        threadPoolTaskScheduler.setThreadNamePrefix( "ThreadPoolTaskScheduler" );
        return threadPoolTaskScheduler;
    }
}
