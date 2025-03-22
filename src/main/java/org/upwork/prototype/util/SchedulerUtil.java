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

package org.upwork.prototype.util;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Schedule Utils
 *
 * @author prasadm
 * @since 20 Dec 2022
 */

public class SchedulerUtil
{
    private static final ScheduledFuture<?>[] SCHEDULED_FUTURE = { null };

    private static final boolean[] RUNNING_STATUS = { false };

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private static final Lock WRITE_LOCK = LOCK.writeLock();

    private static final Lock READ_LOCK = LOCK.readLock();

    private SchedulerUtil()
    {
    }

    public static ScheduledFuture<?> getScheduledFuture()
    {
        try
        {
            READ_LOCK.lock();
            return SCHEDULED_FUTURE[0];
        }
        finally
        {
            READ_LOCK.unlock();
        }
    }

    public static void setScheduledFuture( ScheduledFuture<?> scheduledFuture )
    {
        try
        {
            WRITE_LOCK.lock();
            SCHEDULED_FUTURE[0] = scheduledFuture;
        }
        finally
        {
            WRITE_LOCK.unlock();
        }
    }

    public static boolean getRunningStatus()
    {
        try
        {
            READ_LOCK.lock();
            return RUNNING_STATUS[0];
        }
        finally
        {
            READ_LOCK.unlock();
        }
    }

    public static void setRunningStatus( boolean runningStatus )
    {
        try
        {
            WRITE_LOCK.lock();
            RUNNING_STATUS[0] = runningStatus;
        }
        finally
        {
            WRITE_LOCK.unlock();
        }
    }
}
