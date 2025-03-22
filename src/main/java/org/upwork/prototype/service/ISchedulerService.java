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
