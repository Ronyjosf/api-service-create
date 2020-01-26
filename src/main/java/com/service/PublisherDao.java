package com.service;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class PublisherDao implements Dao<Publisher> {
    Logger logger = getLogger("service");
    private List<Publisher> publishers = new ArrayList<>();

    public void PublisherDao(){
        logger.info("in PublisherDao");
    }
    @Override
    public Optional get(long id) {
        return Optional.ofNullable(publishers.get((int) id));
    }

    @Override
    public List getAll() {
        return publishers;
    }

    @Override
    public void save(Publisher publisher) {
        publishers.add(publisher);
    }

    @Override
    public void update(Publisher publisher, String[] params) {

    }

    @Override
    public void delete(Publisher publisher) {
        publishers.remove(publisher);

    }

    public Publisher getPublisherId(String id) {
        for (Publisher publisher: publishers){
            // classic Error - I implemneted publisher.publisherId.equals(id), and then it took all numbers, if i ask for id=0
            if (publisher.publisherId.equals(id)) {
                return publisher;
            }

        }
        return null;
    }
}
