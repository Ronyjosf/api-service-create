###### Objective:

    A API service listening on http://localhost:4567 ( use Postamn to test)

This is an api service with the following **api calls**:

    get: path = /api/status
        http://localhost:4567/api/status
    get: /api/getOutlierById
        http://localhost:4567/api/getOutlierById?id=200
    get: http://localhost:4567/api/getOutlierById-last-n-readings?id=200&last-n-readings=3
    
    post: /api/loadProviders 
        http://localhost:4567/api/loadProviders
        **body** = {
                   "publisherId": "200",
                   "time": "2015-11-03 15:03:30.352",
                   "readings": [ 1, 13, 192, 7, 8, 99, 1014, 4]
               }
note: in order to do any get, you need to loadProviders first
Run the service:

    via cmd : 
    mvn install
    mvn exec:java -Dexec.mainClass="com.service.Main"
    or run from IDE ( it calls main)
servive would run, and recieves api requests until shutdown.



