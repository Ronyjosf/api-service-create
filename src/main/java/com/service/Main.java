package com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import static spark.Spark.*;

public class Main {
    private static Logger logger;
    private static PublisherDao publisherDao = new PublisherDao();

    public static void main(String[] args) {
        logger = getLogger("service");
//        Gson gson = new Gson();
//        get("/api/temp", (req, res) -> "User: username=test, email=test@test.net");
        get("/api/status", (req, res) -> "status = OK");

        post("/api/loadProviders", "application/json",  (request, response) -> {
            logger.info("attributes = "+request.contentType());
            ObjectMapper mapper = new ObjectMapper();
            Publisher jsonPublisher;
            try {
                jsonPublisher = mapper.readValue(request.body(), Publisher.class);
            }catch (UnrecognizedPropertyException e){
                logger.info("got exception: "+e.getMessage());
                response.status(404);
                return "got exception :"+e.getMessage();
            }
                // Transfer/Map jsonString into Publisher object
            Publisher publisher = new Publisher(jsonPublisher.publisherId, jsonPublisher.time, jsonPublisher.readings);
            publisher.setMedian();
            logger.info("publisher id = " + publisher.publisherId + ", median = " + publisher.getMedian());

            publisherDao.save(publisher);
            logger.info("publisherDao = "+publisherDao.getAll());

            response.status(200);
            return "this is the body: "+request.body();
        });

        get("/api/getOutlierById", (req, res) -> {
            if (req.queryParams().size() <1){
                res.status(404);
                return "no id paramter";
            }

            Publisher pulledPublisher;
             pulledPublisher= publisherDao.getPublisherId(req.queryParams("id"));
             if (pulledPublisher ==null){
                res.status(404);
                return "fail to find publisher per id = "+req.queryParams("id");
            }

            logger.info("pulled publisher id "+pulledPublisher.publisherId);
            return (Utils.getLargestNum (pulledPublisher.readings));
//            return "User: username=test, email=test@test.net";
        });

        get("/api/getOutlierById-last-n-readings", (req, res) -> {
            if (req.queryParams().size() <1){
                res.status(404);
                return "no 'id' paramter, or 'last-n-readings'";
            }
            Publisher pulledPublisher;
            pulledPublisher= publisherDao.getPublisherId(req.queryParams("id"));
            if (pulledPublisher ==null){
                res.status(404);
                return "coulnd' find publisher id = "+req.queryParams("id");
            }

            logger.info("pulled publisher id "+pulledPublisher.publisherId);
            return (Utils.getLargestNum (pulledPublisher.readings));
//            return "User: username=test, email=test@test.net";
        });
    }
}

//{
//        "publisher": "publisher-id",
//        "time": "2015-11-03 15:03:30.352",
//        "readings": "[ 1, 13, 192, 7, 8, 99, 1014, 4]"
//        }
