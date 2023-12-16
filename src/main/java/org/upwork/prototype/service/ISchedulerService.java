package org.upwork.prototype.service;

import org.upwork.prototype.util.Response;
import org.upwork.prototype.util.ResponseError;

/**
 * Scheduler Service
 *
 * @author prasadm
 * @since 03 Dec 2022
 */

public interface ISchedulerService
{
    Response<String> startJob( String cronExpression ) throws ResponseError;

    Response<String> stopJob() throws ResponseError;
}
