package com.gl.loggly.glbookauther.service;


import com.gl.loggly.glbookauther.model.AuthorCustomSequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Service
public class NextSequenceService {
    @Autowired private MongoOperations mongo;

    public int getNextSequence(String seqName)
    {
        AuthorCustomSequences counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            AuthorCustomSequences.class);
        return counter.getSeq();
    }
}