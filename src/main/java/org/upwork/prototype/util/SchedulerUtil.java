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
