package org.upwork.prototype.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upwork.prototype.util.ResponseWrapper;

/**
 * Scheduler REST Controller
 *
 * @author prasadm
 * @since 03 Dec 2022
 */

@RequestMapping( "/v1/scheduler" )
public interface ISchedulerController
{
    @GetMapping( value = "/start", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<String>> startScheduledJob( @RequestParam( value = "cron" ) String cronExpression );

    @GetMapping( value = "/stop", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<String>> stopScheduledJob();
}
