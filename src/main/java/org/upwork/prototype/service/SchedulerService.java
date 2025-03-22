/*
 * Copyright 2023 Prasad Madusanka Basnayaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.upwork.prototype.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.upwork.prototype.util.Response;
import org.upwork.prototype.util.ResponseError;
import org.upwork.prototype.util.SchedulerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Scheduler Service
 *
 * @author prasadm
 * @since 03 Dec 2022
 */

@Service
public class SchedulerService implements ISchedulerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger( SchedulerService.class );

    private final SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Override
    public Response<String> startJob( String cronExpression ) throws ResponseError
    {
        if( SchedulerUtil.getRunningStatus() )
        {
            LOGGER.info( ">>>>> Job is running already <<<<<" );
            return new Response<>( "Job is running already" );
        }
        SchedulerUtil.setScheduledFuture( taskScheduler.schedule( new RunnableTask(), new CronTrigger( cronExpression ) ) );
        SchedulerUtil.setRunningStatus( true );
        LOGGER.info( ">>>>> Job started <<<<<" );
        return new Response<>( "Job started" );
    }

    @Override
    public Response<String> stopJob() throws ResponseError
    {
        if( !SchedulerUtil.getRunningStatus() )
        {
            LOGGER.info( ">>>>> Job has been stopped already <<<<<" );
            return new Response<>( "Job has been stopped already" );
        }
        boolean status = SchedulerUtil.getScheduledFuture().cancel( false );
        SchedulerUtil.setRunningStatus( false );
        LOGGER.info( status ? ">>>>> Job stopped <<<<<" : ">>>>> Failed to stop job <<<<<" );
        return new Response<>( status ? "Job stopped" : "Failed to stop job" );
    }

    private class RunnableTask implements Runnable
    {
        @Override
        public void run()
        {
            // TODO :: add your custom code here
            final String str = ">>>>> Hello World >>>>> " + formatter.format( new Date() );
            LOGGER.info( str );
        }
    }
}
