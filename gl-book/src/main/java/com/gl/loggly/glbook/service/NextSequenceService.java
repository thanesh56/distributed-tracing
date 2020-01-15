package com.gl.loggly.glbook.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.gl.loggly.glbook.model.BookCustomSequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;




@Service
public class NextSequenceService {
    @Autowired private MongoOperations mongo;

    public int getNextSequence(String seqName)
    {
        BookCustomSequences counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            BookCustomSequences.class);
        return counter.getSeq();
    }
}