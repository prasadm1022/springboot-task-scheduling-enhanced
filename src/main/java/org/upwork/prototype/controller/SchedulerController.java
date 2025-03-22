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

package org.upwork.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.upwork.prototype.service.ISchedulerService;
import org.upwork.prototype.util.Response;
import org.upwork.prototype.util.ResponseError;
import org.upwork.prototype.util.ResponseUtil;
import org.upwork.prototype.util.ResponseWrapper;

/**
 * Scheduler REST Controller
 *
 * @author prasadm
 * @since 03 Dec 2022
 */

@RestController
public class SchedulerController implements ISchedulerController
{
    @Autowired
    private ISchedulerService schedulerService;

    @Override
    public ResponseEntity<ResponseWrapper<String>> startScheduledJob( String cronExpression )
    {
        try
        {
            Response<String> response = schedulerService.startJob( cronExpression );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<String>> stopScheduledJob()
    {
        try
        {
            Response<String> response = schedulerService.stopJob();
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
