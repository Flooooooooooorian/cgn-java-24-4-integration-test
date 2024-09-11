package de.neuefische.cgnjava244mocking;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "cats")
public record Cat(
        @MongoId
        String id,
        String name,
        String color
) {
}
