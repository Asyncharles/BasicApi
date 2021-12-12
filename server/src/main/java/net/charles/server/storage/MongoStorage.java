package net.charles.server.storage;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MongoStorage extends AbstractStorage {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoStorage() {
        super("MongoDB");
    }

    @Override
    public void init() {
        final String connection = "";
        final ConnectionString connectionString = new ConnectionString(connection);

        final CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new UuidCodecProvider(UuidRepresentation.JAVA_LEGACY)),
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(Document.class)
                        .automatic(true)
                        .build())
        );

        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .build());
        database = mongoClient.getDatabase("BasicApi");
        userCollection = database.getCollection("user", Document.class);
        userCollection.createIndex(Indexes.hashed("uuid"), new IndexOptions().background(true)).subscribe(
                new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Integer.MAX_VALUE);
                        System.out.println("Indexed subscribed");
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Created indexes");
                    }
                }
        );
    }

    @Override
    public void shutdown() {
        mongoClient.close();
    }
}
