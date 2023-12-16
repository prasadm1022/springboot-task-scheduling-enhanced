## Explanation :

### docker-compose-backend.yml

#### **backend-server-scheduler :**
* This will be responsible for executing tasks related to scheduler.
* Only one replica must be run from this service.

#### **backend-server-normal :**
* All other tasks will be run on this service & it's replicas.
* This can be scale to any number of replicas when needed. (currently only two nodes will be started according to the configs)

#### **load-balancer :**
* If an endpoint of above services has been called by a user, then that will be redirected to the relevant service.
* If the request is for "SchedulerController", then it will be redirected to the "backend-server-scheduler".
* If the request is for other controllers, then those will be redirected to the "backend-server-normal".

### Example :
* Run below command to start the server. This will start one container from "backend-server-scheduler", two containers from "backend-server-normal" and the load balancer container.
>> docker-compose -f docker-compose-backend.yml up
* To start the scheduler :
> > curl --location --request GET 'http://localhost:7000/scheduler-service/v1/scheduler/start?cron=0/5 * * * * *'
* To stop the scheduler :
> > curl --location --request GET 'http://localhost:7000/scheduler-service/v1/scheduler/stop'