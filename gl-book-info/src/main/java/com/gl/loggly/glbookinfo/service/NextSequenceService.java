package com.gl.loggly.glbookinfo.service;



import com.gl.loggly.glbookinfo.model.BookInfoCustomSequences;
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
        BookInfoCustomSequences counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            BookInfoCustomSequences.class);
        return counter.getSeq();
    }
}