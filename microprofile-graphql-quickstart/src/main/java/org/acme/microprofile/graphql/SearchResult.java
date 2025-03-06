package org.acme.microprofile.graphql;

import io.smallrye.graphql.api.Union;

@Union
public interface SearchResult {
    // 此接口用于 GraphQL union，内部可以为空
}
